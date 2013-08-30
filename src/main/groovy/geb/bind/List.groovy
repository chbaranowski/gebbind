package geb.bind


class List extends BindElement {

    ListItem getAt(int index){
        new ListItem(element: selector()[index])
    }

    int size() {
        selector().size()
    }

}
