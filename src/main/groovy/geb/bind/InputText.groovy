package geb.bind

import geb.bind.BindElement
import geb.bind.TextInputPossible


class InputText extends BindElement implements TextInputPossible {

    void insertText(String text) {
        selector().value(text)
    }

    String getValue() {
        selector().value()
    }

}
