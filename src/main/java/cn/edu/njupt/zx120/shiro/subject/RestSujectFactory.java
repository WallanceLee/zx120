package cn.edu.njupt.zx120.shiro.subject;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * Created by wallance on 2/22/17.
 */
public class RestSujectFactory extends DefaultWebSubjectFactory {
    @Override
    public Subject createSubject(SubjectContext context) {
        context.setSessionCreationEnabled(true);
        return super.createSubject(context);
    }
}
