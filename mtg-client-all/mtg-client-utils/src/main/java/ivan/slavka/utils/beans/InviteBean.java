package ivan.slavka.utils.beans;

import ivan.slavka.utils.enums.InvitationAnswerEnum;
import ivan.slavka.utils.enums.InvitationTypeEnum;
import ivan.slavka.utils.enums.PlayerEnum;

public class InviteBean {

	private InvitationTypeEnum type;
	private String from;
	private String to;
	private InvitationAnswerEnum answer;
	private String sessionId;
	private PlayerEnum turnOwner;

	public InvitationTypeEnum getType() {
		return this.type;
	}
	public void setType(InvitationTypeEnum type) {
		this.type = type;
	}
	public String getFrom() {
		return this.from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return this.to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public InvitationAnswerEnum getAnswer() {
		return this.answer;
	}
	public void setAnswer(InvitationAnswerEnum answer) {
		this.answer = answer;
	}
	public String getSessionId() {
		return this.sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public PlayerEnum getTurnOwner() {
		return this.turnOwner;
	}
	public void setTurnOwner(PlayerEnum turnOwner) {
		this.turnOwner = turnOwner;
	}
}
