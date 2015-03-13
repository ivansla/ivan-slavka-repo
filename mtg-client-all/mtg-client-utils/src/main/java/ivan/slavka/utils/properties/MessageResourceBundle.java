package ivan.slavka.utils.properties;

import java.util.Locale;
import java.util.ResourceBundle;


public class MessageResourceBundle{

	private static final long serialVersionUID = 1L;
	private static final String BASE_NAME = "MessageBundle";

	private static MessageResourceBundle resourceBundleAdapter;

	private ResourceBundle resourceBundle;

	public static MessageResourceBundle getInstance() {
		if (resourceBundleAdapter == null) {
			Locale locale = new Locale("en_US");
			resourceBundleAdapter = new MessageResourceBundle(locale);
		}

		return resourceBundleAdapter;
	}

	private MessageResourceBundle(Locale locale) {
		this.resourceBundle = ResourceBundle.getBundle(BASE_NAME, locale);
	}

	public String getMessage(String key, Object... args) {
		return String.format(this.resourceBundle.getString(key), args);
	}


}
