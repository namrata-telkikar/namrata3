package taxPerson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataDrivenTestDataSetup {
	public static String SpreadsheetName;
	public static String SpreadsheetTabName;
	public class SimpleIterator implements Iterator<Object> {

		Object[][] createDataObject = createData(SpreadsheetName, SpreadsheetTabName);
		private int currentIndex;

		public SimpleIterator() { // constructor
			currentIndex = 0;
		}

		public boolean hasNext() {
			return currentIndex < createDataObject.length;
		}

		public Object next() {
			int index = currentIndex;
			currentIndex++;
			// System.out.println("Next " + index);
			String[] data = null;
			data = new String[createDataObject[index].length];
			for (int j = 0; j < createDataObject[index].length; j++) {
				data[j] = (String) createDataObject[index][j];
				// System.out.println("Next data returned is " + data[j]);
			}
			return new Object[] { data };
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	@DataProvider(name = "runner")
	// , parallel=true)
	public Iterator<Object> createParallelPlan() {
		return new SimpleIterator();
	}

	@DataProvider(name = "myTest")
	public Object[][] createData(String SpreadsheetName, String SpreadsheetTabName) {

		TestDataReader testDataReader = new TestDataReader();
		List<ArrayList<String>> dataList = testDataReader.getData(SpreadsheetName, SpreadsheetTabName);

		Object obj[][] = new Object[dataList.size() - 1][dataList.get(0).size()];
		for (int i = 1; i < dataList.size(); i++) {
			for (int j = 0; j < dataList.get(0).size(); j++) {
				obj[i - 1][j] = dataList.get(i).get(j);
			}
		}
		return obj;
	}
}