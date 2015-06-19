package by.bsu.taglib.tag;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Custom Tag
 * @see TagSupport
 */
public class UserInfoTag extends TagSupport {
    private static final Logger LOG = Logger.getLogger(UserInfoTag.class);
    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        String role = session.getAttribute("userRole").toString();
        String locale = (String) session.getAttribute("locale");
        try {
            JspWriter writer = pageContext.getOut();
            writer.write("<h6>" + role + " " + locale + "</h6>");
        } catch (IOException e) {
            LOG.error(e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
    return EVAL_PAGE;
    }
}
