package org.example.mvc.view;

public class JspViewResolver implements ViewResolver {
    @Override
    public View resolveView(String viewName) {
        if (viewName.startsWith(RedirectView.DEFAULT_REDIRECT_PREFIIX)) {
            return new RedirectView(viewName);
        }

        return new JspView(viewName + ".jsp");
    }
}
