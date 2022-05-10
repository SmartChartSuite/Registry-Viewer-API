package io.swagger;

import com.fasterxml.jackson.databind.util.StdDateFormat;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RFC3339DateFormat extends StdDateFormat {

  private static final long serialVersionUID = 1L;

  // Same as ISO8601DateFormat but serializing milliseconds.
  @Override
  public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
    DateFormat dateFormat = new SimpleDateFormat(StdDateFormat.DATE_FORMAT_STR_ISO8601);
    String value = dateFormat.format(date);
    
    toAppendTo.append(value);
    return toAppendTo;
  }

}