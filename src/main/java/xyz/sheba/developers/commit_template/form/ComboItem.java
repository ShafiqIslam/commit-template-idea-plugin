package xyz.sheba.developers.commit_template.form;

public abstract class ComboItem {
    protected String text;
    protected HasComboItem value;

    public ComboItem(HasComboItem value, String text) {
        this.text = text;
        this.value = value;
    }

    public HasComboItem getValue() {
        return value;
    }

    @Override
    public String toString() {
        return text;
    }
}
