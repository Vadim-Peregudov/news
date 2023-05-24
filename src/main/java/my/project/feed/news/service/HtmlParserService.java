package my.project.feed.news.service;

/**
 * This interface provides functionality for parsing HTML templates.
 */
public interface HtmlParserService {
    /**
     * Parses an HTML template and returns the processed HTML as a string.
     *
     * @param templateName   the name of the HTML template to parse
     * @param nameAttribute  the name of the variable to be used in the template
     * @param valueAttribute the value of the variable to be passed to the template
     * @return the parsed HTML as a string
     */
    String parseHtml(String templateName, String nameAttribute, Object valueAttribute);
}
