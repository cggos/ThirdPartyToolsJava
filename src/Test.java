import java.io.IOException;

import ghc.files.PropertiesOpt;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		PropertiesOpt.GetValueByKey("src/database.properties", "jdbc.url");		
	}
}
