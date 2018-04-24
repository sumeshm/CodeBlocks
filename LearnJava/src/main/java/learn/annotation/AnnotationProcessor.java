package learn.annotation;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes({ "learn.annotation.Menu" })
public class AnnotationProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

		if (roundEnv.processingOver()) {
			return true;
		}

        for (Element element : roundEnv.getElementsAnnotatedWith(Menu.class)) {
            Menu menu = element.getAnnotation(Menu.class);
        }

        return false;
	}

}
