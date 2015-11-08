package Model;

public enum OpinionValue {
	POSITIVE,
	NEUTRAL,
	NEGATIVE;
	
	@Override
	public String toString(){
		return this.name().toLowerCase();
	}
	
	public static OpinionValue toOpinionValue(String opinion) {
		OpinionValue result;
		if (opinion.equals("Commentaire negatif")) {
			result = OpinionValue.NEGATIVE;
		} else if (opinion.equals("Commentaire neutre")) {
			result = OpinionValue.NEUTRAL;
		} else {
			result = OpinionValue.POSITIVE;
		}
		return result;
	}
}