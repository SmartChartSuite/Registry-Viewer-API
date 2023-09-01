package io.swagger.dbo;

public class Util {
    public static String getDefaultDataSchema() {
        String ret = System.getenv("JDBC_DATA_SCHEMA");
        if (ret == null || ret.isBlank()) {
            return "public";
        } else {
            return ret;
        }
    }

    public static String getDefaultVocabsSchema() {
        String ret = System.getenv("JDBC_VOCABS_SCHEMA");
        if (ret == null || ret.isBlank()) {
            return "public";
        } else {
            return ret;
        }
    }

    public static String getDefaultViewerSchema() {
        return "viewer";
    }
}
