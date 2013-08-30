package geb.bind


class Table extends BindElement {

    def findCells = { row ->
        row.find('td')
    }

    Table configure(Map options) {
        this.selector = options.rows
        if(options.cells) {
            this.findCells = options.cells
        }
        return this
    }

    Row getAt(int index) {
        new Row(rowElement: selector()[index])
    }

    int size() {
        selector().size()
    }

    class Row {

        def rowElement

        Cell getAt(int index) {
            new Cell(cellElement: findCells(rowElement)[index])
        }

        int size() {
            findCells(rowElement).size()
        }

    }

    class Cell {

        def cellElement

        String text() {
            cellElement.text()
        }

    }

}


