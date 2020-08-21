package at.campus02.itw.awatar.atwatarcore.awatarcore.dto;

public class AwatarTestStepDTO {

    private int number;
    private String textDescription;
    private String codeMethodName;

    public AwatarTestStepDTO(int number, String textDescription, String codeMethodName) {
        this.number = number;
        this.textDescription = textDescription;
        this.codeMethodName = codeMethodName;
    }

    public AwatarTestStepDTO() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public String getCodeMethodName() {
        return codeMethodName;
    }

    public void setCodeMethodName(String codeMethodName) {
        this.codeMethodName = codeMethodName;
    }
}
