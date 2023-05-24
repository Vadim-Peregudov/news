package my.project.feed.news.service.impl;

import my.project.feed.news.service.HtmlParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Implementation of the HtmlParserService interface that parses HTML templates.
 * Uses the TemplateEngine to process the templates.
 */
@Service
public class HtmlParserServiceImpl implements HtmlParserService {

    private final TemplateEngine templateEngine;

    @Autowired
    public HtmlParserServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    /**
     * Parses an HTML template using the provided template engine and returns the processed HTML.
     *
     * @param templateName   the name of the HTML template to parse
     * @param nameAttribute  the name of the variable to be used in the template
     * @param valueAttribute the value of the variable to be passed to the template
     * @return the parsed HTML as a string
     */
    @Override
    public String parseHtml(String templateName, String nameAttribute, Object valueAttribute) {
        // Create a new context and set the variable
        Context context = new Context();
        context.setVariable(nameAttribute, valueAttribute);
        // Process the template using the template engine
        return templateEngine.process(templateName, context);
    }
}
