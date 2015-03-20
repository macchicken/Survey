package survey.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class BeerAttributeListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		System.out.println("Attribute added: " + se.getName() + ": " + se.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		System.out.println("Attribute removed: " + se.getName() + ": " + se.getValue());
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		System.out.println("Attribute replaced: " + se.getName() + ": " + se.getValue());
	}

}
