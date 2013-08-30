package geb.bind

class SampleBind extends Bind {

    def start() {
        go '/index.html'
        waitFor { title == 'GebBind-Test' }
    }

    InputText nameTextInput = inputText {
        $('form').find('input', name: 'firstname')
    }

    Button resetButton = button {
        $('form').find('input', name: 'submit')
    }

    Button someButton = button onClick: {
        $('form').find('input', name: 'someButton').click()
        waitFor {
            infoText == 'some button clicked'
        }
    }

    Text infoText = text {
        $('#info-text')
    }

    List ul = list {
        $('li')
    }

    Table table = table {
        $('tr').filter('.row')
    }

    Table namesTable = table (
        rows: {
            $('tr').filter('.row')
        },
        cells: { row ->
            row.find('td').filter('.name')
        }
    )

}
