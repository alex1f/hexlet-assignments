package exercise;

// BEGIN
public class LabelTag implements TagInterface{
    private String name;
    private TagInterface tag;


    public LabelTag(String name, TagInterface tag) {
        this.tag = tag;
        this.name = name;
    }

    @Override
    public String render() {
        return String.format("<label>%s%s</label>", name, tag.render());
    }
}
// END
