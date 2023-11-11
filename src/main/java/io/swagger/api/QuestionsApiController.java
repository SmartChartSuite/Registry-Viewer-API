package io.swagger.api;

import io.swagger.configuration.RegistryConfig;
import io.swagger.dbo.QuestionRowMapper;
import io.swagger.dbo.Util;
import io.swagger.model.Questions;
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

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-05-05T05:59:28.112Z[GMT]")
@RestController
public class QuestionsApiController implements QuestionsApi {

    private static final Logger log = LoggerFactory.getLogger(QuestionsApiController.class);

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @Autowired
    @Qualifier("viewerJdbcTemplate")
    private JdbcTemplate viewerJdbcTemplate;

    @Autowired
    private RegistryConfig registryConfig;
    
    @Autowired
    public QuestionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Questions> getQuestions(
        @NotNull @Parameter(in = ParameterIn.PATH, description = "Registry Path",required = true,schema = @Schema()) @Valid @PathVariable(value="registry", required = true) String registryPath,
        @NotNull @Parameter(in = ParameterIn.QUERY, description = "section that we are interested." ,required=true,schema=@Schema()) @Valid @RequestParam(value = "section", required = true) String section) {

        if (!registryConfig.isValidRegistry(registryPath)) {
            return new ResponseEntity<Questions>(HttpStatus.NOT_FOUND);
        }
                
        // String accept = request.getHeader("Accept");
        String viewerSchemaName = Util.getDefaultViewerSchema();

        // if (accept != null && accept.contains("application/json")) {
        Questions questions = new Questions();
        questions.addAll(viewerJdbcTemplate.query("SELECT c.concept_id AS ConceptId, c.section AS Section, c.category AS Category, c.question AS Question FROM " + viewerSchemaName + ".category c WHERE section='" + section + "'", new QuestionRowMapper()));
        
        return new ResponseEntity<Questions>(questions, HttpStatus.OK);
    }

}
