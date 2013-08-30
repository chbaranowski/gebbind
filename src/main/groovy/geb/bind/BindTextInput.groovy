package geb.bind


class BindTextInput {

    String text

    void into(TextInputPossible element) {
        element.insertText(text)
    }

}
