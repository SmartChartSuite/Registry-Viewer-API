package io.swagger.api;

import io.swagger.dbo.QuestionRowMapper;
import io.swagger.dbo.CaseDataRowMapper;
import io.swagger.dbo.DetailsRowMapper;
import io.swagger.dbo.FactRelationshipRowMapper;
import io.swagger.dbo.RegistryCaseInfoRowMapper;
import io.swagger.dbo.ViewerAnnotationRowMapper;
import io.swagger.dbo.ViewerFlagRowMapper;
import io.swagger.model.Annotation;
import io.swagger.model.CaseData;
import io.swagger.model.CaseInfo;
import io.swagger.model.Content;
import io.swagger.model.DetailObservation;
import io.swagger.model.DetailUserData;
import io.swagger.model.Details;
import io.swagger.model.FactRelationship;
import io.swagger.model.ManualCaseData;
import io.swagger.model.Question;
import io.swagger.model.UserFlagAnnotationManualData;
import io.swagger.model.ViewerAnnotation;
import io.swagger.model.ViewerFlag;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-04-14T14:20:46.754Z[GMT]")
@RestController
public class CaseRecordApiController implements CaseRecordApi {

    private static final Logger log = LoggerFactory.getLogger(CaseRecordApiController.class);
    private static final long observation_concept_code_min = 2000000000L;

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    @Qualifier("registryJdbcTemplate")
    private JdbcTemplate registryJdbcTemplate;

    @Autowired
    @Qualifier("viewerJdbcTemplate")
    private JdbcTemplate viewerJdbcTemplate;

    @org.springframework.beans.factory.annotation.Autowired
    public CaseRecordApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addUserFlagAnnotationManualData(@NotNull @Parameter(in = ParameterIn.QUERY, description = "" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "caseId", required = true) Integer caseId,@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "contentId", required = false) Integer contentId,@Parameter(in = ParameterIn.DEFAULT, description = "create or update flag, annotations, or user data", schema=@Schema()) @Valid @RequestBody UserFlagAnnotationManualData body) {    
        String accept = request.getHeader("Accept");
        String sql;

        // See if we have a flag information.
        boolean created = false;
        boolean processed = false;

        String flag = body.getFlag();
        if (!"Unknown".equals(flag) && caseId != null && contentId != null) {
            sql = "SELECT * FROM flag WHERE content_id = " + contentId + " AND case_id = " + caseId;
            List<ViewerFlag> viewerFlags = viewerJdbcTemplate.query(sql, new ViewerFlagRowMapper());
            if (viewerFlags.size() > 0) {
                // we update flag. 
                sql = "UPDATE flag SET flag = '" + flag + "'"
                    + " WHERE content_id = " + contentId + " AND case_id = " + caseId;
                viewerJdbcTemplate.update(sql);
            } else {
                sql = "INSERT INTO flag"
                    + " (content_id, flag, case_id)"
                    + " VALUES (" + contentId + ","
                    + " '" + body.getFlag() + "',"
                    + " " + caseId + ")";
                viewerJdbcTemplate.update(sql);
                created = true;
            }

            processed = true;
        }

        // See if we need to create/update annotations
        @Valid
        List<Annotation> annotations = body.getAnnotations();
        if (annotations != null && caseId != null && contentId != null) {
            for (Annotation annotation : annotations) {
                Integer annotationId = annotation.getAnnotationId();
                if (annotationId == null || annotationId == 0) {
                    if (annotation.getText() == null || annotation.getText().isEmpty()) {
                        // nothing to do on the empty text.
                        continue;
                    }
                    // this is new one.
                    sql = "INSERT INTO annotation"
                        + " (content_id, case_id, text)"
                        + " VALUES (" + contentId + ","
                        + " " + caseId + ","
                        + " '" + annotation.getText() + "')";
                    viewerJdbcTemplate.update(sql);
                    created = true;
                } else {
                    // update
                    if (annotation.getText() == null || annotation.getText().isEmpty()) {
                        // empty text with annotation id. Delete this.
                        sql = "DELETE FROM annotation WHERE annotation_id = " + annotationId;
                    } else {
                        sql = "UPDATE annotation SET text = '" + annotation.getText() + "'"
                        + " WHERE annotation_id = " + annotationId;
                    }

                    viewerJdbcTemplate.update(sql);
                }
            }

            processed = true;
        }

        @Valid
        List<ManualCaseData> manualCaseDatas = body.getManualCaseData();
        if (manualCaseDatas != null && caseId != null) {
            // Get patient id.
            sql = "SELECT person_id AS PersonId FROM case_info WHERE case_info_id = " + caseId;
            List<CaseInfo> caseInfos = registryJdbcTemplate.query(sql, new RegistryCaseInfoRowMapper());
            if (caseInfos.isEmpty()) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }

            Integer personId = caseInfos.get(0).getPersonId();
            for (ManualCaseData mannualCaseData : manualCaseDatas) {
                Integer conceptId = mannualCaseData.getConceptId();
                String value = mannualCaseData.getValue();
                String dateString = mannualCaseData.getDate();
                

                // Date date = new Date();
                // if (dateString != null && !dateString.isEmpty()) {
                //     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                //     try {
                //         date = sdf.parse(dateString);
                //     } catch (ParseException e) {
                //         e.printStackTrace();
                //         continue;
                //     }
                // }

                sql = "INSERT INTO observation (observation_id, person_id,"
                    + " observation_date, observation_concept_id,"
                    + " observation_type_concept_id, value_as_string, observation_source_value) SELECT"
                    + " coalesce(max(observation_id), 0)+1,"
                    + " " + personId + ","
                    + " '" + dateString + "',"
                    + " " + conceptId + ","
                    + " 36685765,"
                    + " '" + value + "',"
                    + " '" + value + "'"
                    + " FROM observation";
                registryJdbcTemplate.update(sql);
                created = true;
            }

            processed = true;
        }

        if (processed == false) {
            return new ResponseEntity<Void>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        
        if (created) {
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    private Integer getConceptCodeForSection(String section) {
        List<Question> questions = viewerJdbcTemplate.query("SELECT c.concept_id AS ConceptId, c.section AS Section, c.category AS Category, c.question AS Question FROM category c WHERE section='" + section + "'", new QuestionRowMapper());
        if (questions.size()>0) {
            return questions.get(0).getConceptId();
        } else {
            return 0;
        }
    }

    private String createSearchSqlStatement (Integer caseId, String sectionsToSend) {
        String sqlSelectFrom = "SELECT"
            + " o.observation_date AS Date, "
            + " o.observation_id AS ObservationId, "
            + " o.observation_concept_id AS ObservationConceptId, "
            + " oc.vocabulary_id AS ObservationConceptSystem, "
            + " oc.concept_code AS ObservationConceptCode, "
            + " oc.concept_name AS ObservationConceptName, "
            + " ot.vocabulary_id AS ObservationTypeConceptSystem, "
            + " ot.concept_code AS ObservationTypeConceptCode, "
            + " ot.concept_name AS ObservationTypeConceptName, "
            + " o.value_as_string AS DerivedValue, "
            + " o.observation_source_value AS SourceValue "
            + " FROM"
            + " observation o join person p on o.person_id = p.person_id"
            + " join f_person fp on p.person_id = fp.person_id"
            + " join case_info ci on p.person_id = ci.person_id"
            + " left join concept oc on o.observation_concept_id = oc.concept_id"
            + " left join concept ot on o.observation_type_concept_id = ot.concept_id"
            + " WHERE"
            + " ci.case_info_id = " + caseId;
        
        if (sectionsToSend != null && !sectionsToSend.isEmpty()) {
            String[] categories = sectionsToSend.split(",");
            for (String category : categories) {
                Integer concept_code = getConceptCodeForSection(category);
                if (concept_code > 0) {
                    sqlSelectFrom += " AND o.observation_concept_id = " + concept_code;
                }
            }
        } else {
            sqlSelectFrom += " AND o.observation_concept_id > " + observation_concept_code_min;
        }

        return sqlSelectFrom;
    }

    void addDetails(Content content) {
        Integer observationId = content.getContentId();

        String sql = "SELECT"
            + " f.domain_concept_id_1 AS domain_concept_id_1,"
            + " f.fact_id_1 AS fact_id_1,"
            + " f.domain_concept_id_2 AS domain_concept_id_2,"
            + " f.fact_id_2 AS fact_id_2,"
            + " f.relationship_concept_id AS relationship_concept_id"
            + " FROM"
            + " fact_relationship f join observation o on o.observation_id = f.fact_id_1"
            + " WHERE"
            + " o.observation_id = " + observationId
            + " AND f.relationship_concept_id = " + 44818759L;
        
        List<FactRelationship> factRelationships = registryJdbcTemplate.query(sql, new FactRelationshipRowMapper());
        Details details = new Details();

        if (factRelationships == null || factRelationships.size() ==0) {
            // We have no detail. But we still need to populate tableDisplayText.
            DetailUserData detailUserData = new DetailUserData();
            detailUserData.setTableDisplayText(content.getDerivedValue().getValue());
            details.add(detailUserData);
        } else {
            // From the factrelationship, set get resource name to read for each realtionship.
            for (FactRelationship factRelationship : factRelationships) {
                Integer domainId = factRelationship.getDomainConceptId2();
                Integer entityId = factRelationship.getFactId2();
                if (domainId == 13L) { // Drug_Exposure
                    sql = "SELECT"
                        + " d.drug_exposure_start_date AS startDate,"
                        + " d.drug_exposure_end_date AS endDate,"
                        + " c.vocabulary_id AS System,"
                        + " c.concept_code AS Code,"
                        + " c.concept_name AS Display,"
                        + " d.refills AS Refills,"
                        + " d.quantity AS Quantity,"
                        + " d.days_supply AS DaysSupply,"
                        + " d.sig AS Sig,"
                        + " cr.vocabulary_id AS RouteSystem,"
                        + " cr.concept_code AS RouteCode,"
                        + " cr.concept_name AS RouteDisplay,"
                        + " d.lot_number AS LotNumber"
                        + " FROM drug_exposure d join concept c on d.drug_concept_id = c.concept_id"
                        + " left join concept cr on d.route_concept_id = cr.concept_id"
                        + " WHERE d.drug_exposure_id = " + entityId;
                } else if (domainId == 19L) { // condition_occurrence
                    sql = "SELECT"
                        + " cd.condition_start_date AS startDate,"
                        + " cd.condition_end_date AS endDate,"
                        + " c.vocabulary_id AS System,"
                        + " c.concept_code AS Code,"
                        + " c.concept_name AS Display"
                        + " FROM condition_occurrence cd join concept c on cd.condition_concept_id = c.concept_id"
                        + " WHERE cd.condition_occurrence_id = " + entityId;
                } else if (domainId == 27L) { // observation
                    sql = "SELECT"
                        + " o.observation_date AS Date,"
                        + " c.vocabulary_id AS System,"
                        + " c.concept_code AS Code,"
                        + " c.concept_name AS Display,"
                        + " o.value_as_number AS ValueAsNumber,"
                        + " o.value_as_string AS ValueAsString,"
                        + " cv.vocabulary_id AS ValueAsConceptSystem,"
                        + " cv.concept_code AS ValueAsConceptCode,"
                        + " cv.concept_name AS ValueAsConceptDisplay,"
                        + " cu.concept_name AS Unit"
                        + " FROM observation o join concept c on o.observation_concept_id = c.concept_id"
                        + " left join concept cv on o.value_as_concept_id = cv.concept_id"
                        + " left join concept cu on o.unit_concept_id = cu.concept_id"
                        + " WHERE o.observation_id = " + entityId;
                } else if (domainId == 21L) { // measurement
                    sql = "SELECT"
                        + " m.measurement_date AS Date,"
                        + " c.vocabulary_id AS System,"
                        + " c.concept_code AS Code,"
                        + " c.concept_name AS Display,"
                        + " co.concept_name AS Operator,"
                        + " m.value_as_number AS ValueAsNumber,"
                        + " m.value_source_value AS ValueSourceValue,"
                        + " cv.vocabulary_id AS ValueAsConceptSystem,"
                        + " cv.concept_code AS ValueAsConceptCode,"
                        + " cv.concept_name AS ValueAsConceptDisplay,"
                        + " cu.concept_name AS Unit,"
                        + " m.range_low AS RangeLow,"
                        + " m.range_high AS RangeHigh"
                        + " FROM measurement m join concept c on m.measurement_concept_id = c.concept_id"
                        + " left join concept cv on m.value_as_concept_id = cv.concept_id"
                        + " left join concept cu on m.unit_concept_id = cu.concept_id"
                        + " left join concept co on m.operator_concept_id = co.concept_id"
                        + " WHERE m.measurement_id = " + entityId;
                } else if (domainId == 5085L) { // note
                    sql = "SELECT"
                        + " n.note_date AS Date,"
                        + " c.vocabulary_id AS System,"
                        + " c.concept_code AS Code,"
                        + " c.concept_name AS Display,"
                        + " n.note_text AS Value"
                        + " FROM note n join concept c on n.note_type_concept_id = c.concept_id"
                        + " WHERE n.note_id = " + entityId;
                } else {
                    log.error("Invalid Domain ConceptID2");
                    continue;
                }

                DetailsRowMapper detailsRowMapper = new DetailsRowMapper(domainId);
                detailsRowMapper.setShortDisplay(content.getDerivedValue().getValue());
                
                details.addAll(registryJdbcTemplate.query(sql, detailsRowMapper));            
            }
        }

        content.setDetails(details);
    }

    public ResponseEntity<CaseData> searchCategory(@NotNull @Parameter(in = ParameterIn.QUERY, description = "case-id for the category" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "caseId", required = true) String caseId,@Parameter(in = ParameterIn.QUERY, description = "sections to query for the case-id" ,schema=@Schema()) @Valid @RequestParam(value = "sections", required = false) String sections) {
        String accept = request.getHeader("Accept");
        Integer caseIdInteger = Integer.valueOf(caseId);

        if (accept != null && accept.contains("application/json")) {
            // Make map for viewer flag
            String sql = "SELECT content_id, flag, case_id FROM flag WHERE case_id = " + caseId;
            ViewerFlagRowMapper viewerFlagRowMapper = new ViewerFlagRowMapper();
            viewerJdbcTemplate.query(sql, viewerFlagRowMapper);
            Map<Integer, ViewerFlag> userFlagMap = viewerFlagRowMapper.getResultMap();

            sql = "SELECT annotation_id, content_id, case_id, user_id, text, created FROM annotation WHERE case_id = " + caseId;
            ViewerAnnotationRowMapper viewerAnnotationRowMapper = new ViewerAnnotationRowMapper();
            viewerJdbcTemplate.query(sql, viewerAnnotationRowMapper);
            Map<Integer, List<ViewerAnnotation>> userAnnotationMap = viewerAnnotationRowMapper.getResultMap();

            sql = "SELECT c.concept_id AS ConceptId, c.section AS Section, c.category AS Category, c.question AS Question FROM category c";
            QuestionRowMapper questionRowMapper = new QuestionRowMapper();
            viewerJdbcTemplate.query(sql, questionRowMapper);

            CaseDataRowMapper caseDataRowMapper = new CaseDataRowMapper(questionRowMapper.getQuestionMap());
            sql = createSearchSqlStatement(caseIdInteger, sections);
            List<Content> registryData = registryJdbcTemplate.query(sql, caseDataRowMapper);
            registryData.removeAll(Collections.singletonList(null));
            
            // Add details to each content
            for (Content content : registryData) {
                ViewerFlag viewerData = userFlagMap.get(content.getContentId());
                List<ViewerAnnotation> viewerAnnotations = userAnnotationMap.get(content.getContentId());
                if (viewerData != null) {
                    String flag = viewerData.getFlag();
                    if (flag != null && !flag.isEmpty() && !"null".equalsIgnoreCase(flag))
                        content.setFlag(viewerData.getFlag());
                }

                if (viewerAnnotations != null) {
                    for (ViewerAnnotation viewerAnnotation : viewerAnnotations) {
                        Annotation annotation = new Annotation();
                        annotation.setAnnotationId(viewerAnnotation.getAnnotationId());
                        annotation.setText(viewerAnnotation.getText());
                        annotation.setDate(viewerAnnotation.getDate());
                        content.addAnnotationItem(annotation);
                    }
                }

                addDetails(content);
            }

            CaseData sectionsResponse = new CaseData();
            sectionsResponse.setContents(registryData);
            sectionsResponse.setCaseId(caseIdInteger);
            sectionsResponse.setCount(registryData.size());

            return new ResponseEntity<CaseData>(sectionsResponse, HttpStatus.OK);
        }

        return new ResponseEntity<CaseData>(HttpStatus.NOT_IMPLEMENTED);
    }

}
