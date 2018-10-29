package hello.bean;

import java.util.List;

public class AskAndAnswers {
	private List<AskAndAnswer> askAndAnswers;

	public List<AskAndAnswer> getAskAndAnswers() {
		return askAndAnswers;
	}

	public void setAskAndAnswers(List<AskAndAnswer> askAndAnswers) {
		this.askAndAnswers = askAndAnswers;
	}

	@Override
	public String toString() {
		return "AskAndAnswers [askAndAnswers=" + askAndAnswers + "]";
	}
	
}
