package io.swagger.api;

import io.swagger.configuration.RegistryConfig;
import io.swagger.dbo.CaseRowMapper;
import io.swagger.dbo.Util;
import io.swagger.model.Cases;
import io.swagger.model.ModelCase;
import io.swagger.util.QueryRequest;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-03-29T13:22:28.494Z[GMT]")
@RestController
public class SearchCasesApiController implements SearchCasesApi {
    private static final Logger log = LoggerFactory.getLogger(SearchCasesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    @Qualifier("registryJdbcTemplate")
    private JdbcTemplate registryJdbcTemplate;
    
    @Autowired
    public SearchCasesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    private RegistryConfig registryConfig;
    
    private String CreateSearchSqlStatement (String dataSchemaName, String terms, String fields) throws Exception {
        String retSql = "";
        if (dataSchemaName == null || dataSchemaName.isEmpty()) {
            throw new NotFoundException(500, "Data Schema Name cannot be null.");
        }
        String vocabSchemaName = Util.getDefaultVocabsSchema();
        
        String sqlSelectFrom = "SELECT"
            + " ci.case_info_id AS CaseId,"
            + " ci.activated_datetime AS ActivatedDateTime,"
            + " ci.created_datetime AS CreatedDateTime,"
            + " ci.trigger_at_datetime AS NextScheduledDateTime,"
            + " ci.last_updated_datetime AS LastUpdatedlDateTime,"
            + " ci.last_successful_datetime AS LastSuccessfulDateTime,"
            + " ci.case_started_running_datetime AS CaseStartedRunningDateTime,"
            + " fp.family_name AS LastName,"
            + " fp.given1_name AS FirstName1,"
            + " fp.given2_name AS FirstName2,"
            + " c.concept_name AS Gender,"
            + " p.year_of_birth AS DobYear,"
            + " p.month_of_birth AS DobMonth,"
            + " p.day_of_birth AS DobDay,"
            + " fp.contact_point1 AS Contact1,"
            + " fp.contact_point2 AS Contact2,"
            + " fp.contact_point3 AS Contact3,"
            + " l.address_1 AS Address1,"
            + " l.address_2 AS Address2,"
            + " l.city AS City,"
            + " l.state AS State,"
            + " l.zip AS Zip,"
            + " ci.status AS Status"
            + " FROM "
            + dataSchemaName + ".person p join " + dataSchemaName + ".f_person fp on p.person_id = fp.person_id"
            + " join " + dataSchemaName + ".case_info ci on p.person_id = ci.person_id"
            + " left join " + dataSchemaName + ".location l on p.location_id = l.location_id"
            + " left join " + vocabSchemaName + ".concept c on p.gender_concept_id = c.concept_id";

        if (terms == null || terms.isEmpty()) {
            retSql = sqlSelectFrom;
        } else {
            String[] values = terms.toLowerCase().split(",");
            String whereString = "";

            String modifierString1 = "LIKE '%";
            String modifierString2 = "%'";

            for (String value : values) {
                String[] mod = value.trim().toLowerCase().split(":");
                String value_ = mod[0];

                if (mod.length == 2) {
                    if ("exact".equals(mod[1])) {
                        modifierString1 = "= '";
                        modifierString2 = "'";
                    } else if ("begin".equals(mod[1])) {
                        modifierString1 = "LIKE '%";
                        modifierString2 = "'";
                    } else if ("end".equals(mod[1])) {
                        modifierString1 = "LIKE '";
                        modifierString2 = "%'";
                    }
                }

                if (fields == null || fields.isEmpty()) {
                    if (!whereString.isEmpty()) {
                        whereString += " AND ";
                    }

                    whereString += "(LOWER(fp.family_name) " + modifierString1 + value_ + modifierString2 + " OR "
                        + "LOWER(fp.given1_name) " + modifierString1 + value_ + modifierString2 + " OR "
                        + "LOWER(fp.given2_name) " + modifierString1 + value_ + modifierString2 + " OR "
                        + "LOWER(c.concept_name) " + modifierString1 + value_ + modifierString2 + " OR "
                        + "LOWER(l.address_1) " + modifierString1 + value_ + modifierString2 + " OR "
                        + "LOWER(l.address_2) " + modifierString1 + value_ + modifierString2 + " OR "
                        + "LOWER(l.city) " + modifierString1 + value_ + modifierString2 + " OR "
                        + "LOWER(l.state) " + modifierString1 + value_ + modifierString2 + " OR "
                        + "LOWER(l.zip) " + modifierString1 + value_ + modifierString2 + " OR "
                        + "LOWER(ci.status) " + modifierString1 + value_ + modifierString2 + ")";
                
                } else {
                    fields = fields.toLowerCase();

                    String subWhere = "";
                    if (fields.contains("lastName")) {
                        subWhere = "LOWER(fp.family_name) " + modifierString1 + value_ + modifierString2;
                    }

                    if (fields.contains("firstName")) {
                        if (subWhere != null && !subWhere.isEmpty()) {
                            subWhere += " AND ";
                        }
                        subWhere += "(LOWER(fp.given1_name) " + modifierString1 + value_ + modifierString2 + " OR LOWER(fp.given2_name) " + modifierString1 + value_ + modifierString2 + ")";
                    }

                    if (fields.contains("gender")) {
                        if (subWhere != null && !subWhere.isEmpty()) {
                            subWhere += " AND ";
                        }
                        subWhere += "LOWER(c.concept_name) " + modifierString1 + value_ + modifierString2;
                    }

                    if (fields.contains("street")) {
                        if (subWhere != null && !subWhere.isEmpty()) {
                            subWhere += " AND ";
                        }
                        subWhere += "(LOWER(l.address_1) " + modifierString1 + value_ + modifierString2 + " OR LOWER(l.address_2) " + modifierString1 + value_ + modifierString2 + ")";
                    }

                    if (fields.contains("city")) {
                        if (subWhere != null && !subWhere.isEmpty()) {
                            subWhere += " AND ";
                        }
                        subWhere += "LOWER(l.city) " + modifierString1 + value_ + modifierString2;
                    }

                    if (fields.contains("state")) {
                        if (subWhere != null && !subWhere.isEmpty()) {
                            subWhere += " AND ";
                        }
                        subWhere += "LOWER(l.state) " + modifierString1 + value_ + modifierString2;
                    }

                    if (fields.contains("zip")) {
                        if (subWhere != null && !subWhere.isEmpty()) {
                            subWhere += " AND ";
                        }
                        subWhere += "LOWER(l.zip) " + modifierString1 + value_ + modifierString2;
                    }

                    if (fields.contains("status")) {
                        String codes = QueryRequest.codesFromUiCode(value_);
                        if (codes != null) {
                            if (subWhere != null && !subWhere.isEmpty()) {
                                subWhere += " AND ";
                            }
                            String[] codeList = codes.split(",");
                            boolean begin = true;
                            for (String c : codeList) {
                                if (begin) {
                                    subWhere += "(LOWER(ci.status) " + modifierString1 + c + modifierString2;
                                    begin = false;
                                } else {
                                    subWhere += "OR LOWER(ci.status) " + modifierString1 + c + modifierString2;
                                }
                            }
                            subWhere += ")";
                        }
                    }

                    if (fields.contains("dob")) {
                        if (subWhere != null && !subWhere.isEmpty()) {
                            subWhere += " AND ";
                        }
                        subWhere += "LOWER(ci.status) " + modifierString1 + value_ + modifierString2;
                    }

                    if (fields.contains("caseid")) {
                        if (subWhere != null && !subWhere.isEmpty()) {
                            subWhere += " AND ";
                        }

                        subWhere += "ci.case_info_id = " + value_;
                    }

                    if (!subWhere.isEmpty()) {
                        if (!whereString.isEmpty()) {
                            whereString += " AND ";
                        }
                        whereString += subWhere;
                    }

                }
            }

            if (whereString.isEmpty()) {
                throw new Exception("fileds = " + fields + " not supported.");
            }

            retSql = sqlSelectFrom + " WHERE " + whereString;
        }

        return retSql;
    }
    public ResponseEntity<Cases> searchCases(
        @NotNull @Parameter(in = ParameterIn.PATH, description = "Registry Path",required = true,schema = @Schema()) @Valid @PathVariable(value="registry", required = true) String registryPath,
        @Parameter(in = ParameterIn.QUERY, description = "search terms for cases" ,schema=@Schema()) @Valid @RequestParam(value = "terms", required = false) String terms,
        @Parameter(in = ParameterIn.QUERY, description = "search columns for cases" ,schema=@Schema()) @Valid @RequestParam(value = "fields", required = false) String fields) {
        // String accept = request.getHeader("Accept");
        // if (accept != null && accept.contains("application/json")) {
        // Search database for terms. Terms are comma separated string values.

        if (!registryConfig.isValidRegistry(registryPath)) {
            return new ResponseEntity<Cases>(HttpStatus.NOT_FOUND);
        }

        String sql = "";
        try {
            sql = CreateSearchSqlStatement (registryPath, terms, fields);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Cases>(HttpStatus.BAD_REQUEST);
        }
        
        log.debug("searchCases:Query: " + sql);
        List<ModelCase> caseList = registryJdbcTemplate.query(sql, new CaseRowMapper());
        Cases cases = new Cases();
        cases.setCount(caseList.size());
        cases.setCases(caseList);

        return new ResponseEntity<Cases>(cases, HttpStatus.OK);
        // }

        // return new ResponseEntity<Cases>(HttpStatus.OK);
    }

}
