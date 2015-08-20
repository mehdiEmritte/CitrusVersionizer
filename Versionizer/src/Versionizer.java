import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Versionizer {

	public static void main(String[] args) {
		
		if (args.length !=3)
		{
			System.out.println("usage :\n Citrus_Root_Dir() version(vxx.yy.zz) date(2015-05-02)");
			return;
		}

		final int PRODUCT_FILE_CITRUS_VERSION_LINE = 3;
		final int PRODUCT_FILE_CITRUS_FULL_VERSION_LINE = 9;
		final int CITRUS_PLUGIN_FILE_CITRUS_FULL_VERSION_LINE = 56;
		
		
		final String FULLVERSION = args[1] + " (" + args[2] + ")";
		
		File citrusProject = new File(args[0]);

		System.out.println("Citrus root folder : '"
				+ citrusProject.getAbsolutePath() + "'.");

		File productFile = new File(citrusProject,
				"Repository/com.solutions.isoneo.citrus.repository/com.solutions.isoneo.citrus.product");

		System.out.println("Product file : '" + productFile.getAbsolutePath()
				+ "'.");

		File citrusMainPluginFile = new File(citrusProject,
				"Plugins/com.solutions.isoneo.citrus/plugin.xml");

		System.out.println("Citrus main plugin file : '"
				+ citrusMainPluginFile.getAbsolutePath() + "'.");

		try {
			List<String> lines = FileUtils.readLines(productFile);

			System.out.println("product version before update : '"
					+ lines.get(PRODUCT_FILE_CITRUS_VERSION_LINE) + "'.");

			lines.set(PRODUCT_FILE_CITRUS_VERSION_LINE,"<product name=\"CITRUS\" uid=\"citrus\" id=\"com.solutions.isoneo.citrus.product\" application=\"com.solutions.isoneo.citrus.application\" version=\"" + args[1] + "\" useFeatures=\"true\" includeLaunchers=\"true\">");

			System.out.println("product version after update : '"
					+ lines.get(PRODUCT_FILE_CITRUS_VERSION_LINE) + "'.");

			/*---*/
			
			System.out.println("full product version before update : '"
					+ lines.get(PRODUCT_FILE_CITRUS_FULL_VERSION_LINE) + "'.");

			lines.set(PRODUCT_FILE_CITRUS_FULL_VERSION_LINE, "Citrus " + FULLVERSION);

			System.out.println("full product version after update : '"
					+ lines.get(PRODUCT_FILE_CITRUS_FULL_VERSION_LINE) + "'.");

			FileUtils.writeLines(productFile, lines);
			
			lines.clear();
			
			/*---*/

			lines = FileUtils.readLines(citrusMainPluginFile);
			
			System.out.println("citrus plugin version before update : '"
					+ lines.get(CITRUS_PLUGIN_FILE_CITRUS_FULL_VERSION_LINE) + "'.");

			lines.set(CITRUS_PLUGIN_FILE_CITRUS_FULL_VERSION_LINE,"               value=\"Citrus IDE "+ FULLVERSION + "\">"); 
			
			System.out.println("citrus plugin version after update : '"
					+ lines.get(CITRUS_PLUGIN_FILE_CITRUS_FULL_VERSION_LINE) + "'.");
			
			FileUtils.writeLines(citrusMainPluginFile, lines);
			
			lines.clear();
			lines = null;
			
			
		} catch (IOException e) {

			System.out.println("an error occured, you know who to blame");
			e.printStackTrace();
		}

	}

}
