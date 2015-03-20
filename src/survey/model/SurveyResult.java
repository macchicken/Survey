package survey.model;

/**
 * A class to hold survey result 
 * Sample code to be used in COMP5347 labs, SIT, University of Sydney
 * @author Ying Zhou
 *
 */
public class SurveyResult {

	private int[] femalePreference, malePreference;
	
	public int[] getFemalePreference() {
		return femalePreference;
	}

	public int[] getMalePreference() {
		return malePreference;
	}

	public SurveyResult(){
		
	}
	
	public SurveyResult(int productNumber){
		femalePreference = new int[productNumber];
		malePreference = new int[productNumber];
		//initializing the preference to zero
		for (int i = 0; i < productNumber; i ++){
			femalePreference[i] = 0;
			malePreference[i] = 0;
		}
	}
	
	/**
	 * 
	 * @param gender 0 = male, 1 = female
	 * @param productIdx
	 */
	public void addPref(int gender, int productIdx){
		if (gender == 1)
			femalePreference[productIdx] ++;
		else
			malePreference[productIdx]++;
	}
	/**
	 * 
	 * @param gender 0 = male, 1 = female
	 * @param productIdx
	 * @return
	 */
	public int getPref(int gender, int productIdx){
		if (gender == 1)
			return femalePreference[productIdx];
		else
			return malePreference[productIdx];
	}
}
