// the orginator

class TextEditor {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    // create momento
    public Momento save(){
        return new Momento(text);
    }
    // restore the previous momento
    public void restore(Momento m){
        this.text=m.getState();
    }
}

//Can consume a lot of memory if many states are stored
//Deep copy may be required for complex objects