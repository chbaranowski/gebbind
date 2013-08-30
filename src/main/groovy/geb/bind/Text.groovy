package geb.bind


class Text extends BindElement {

    @Override
    boolean equals(Object text) {
        selector().text() == text
    }

    @Override
    String toString() {
        selector().text()
    }
}
