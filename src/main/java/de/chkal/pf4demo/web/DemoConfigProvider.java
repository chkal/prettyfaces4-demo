package de.chkal.pf4demo.web;

import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.QueryString;
import org.ocpsoft.rewrite.servlet.config.Redirect;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

public class DemoConfigProvider extends HttpConfigurationProvider {

    @Override
    public Configuration getConfiguration(ServletContext context) {
        return ConfigurationBuilder.begin()

                // initial redirect to /home
                .addRule()
                .when(Path.matches("/"))
                .perform(Redirect.temporary(context.getContextPath() + "/home"))

                // <rewrite match="^/book\.php\?isbn=(\d+)$" substitute="/buch/$1" redirect="302" />
                .addRule()
                .when(Path.matches("/book.php").and(QueryString.parameterExists("isbn")))
                .perform(Redirect.temporary(context.getContextPath() + "/book/{isbn}"))

                // <url-mapping id="booksByYear">
                // <pattern value="/year/#{year}" />
                // <view-id value="/faces/year.xhtml" />
                // </url-mapping>
                .addRule(Join.path("/year/{year}").to("/faces/year.xhtml"));

    }

    @Override
    public int priority() {
        return 0;
    }

}
