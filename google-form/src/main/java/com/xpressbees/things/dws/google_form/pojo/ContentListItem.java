package com.xpressbees.things.dws.google_form.pojo;

/**
 * Created by ajinkyabadve on 12/12/17.
 */

public class ContentListItem {
    public static final int EDIT_TEXT = 1;
    public static final int EDIT_TEXT_PARAGRAPH = 2;
    private int type;
    private String header;
    private boolean isRequired;
    private String hint;
    private String answer;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "ContentListItem{" +
                "type=" + type +
                ", header='" + header + '\'' +
                ", isRequired=" + isRequired +
                ", hint='" + hint + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
