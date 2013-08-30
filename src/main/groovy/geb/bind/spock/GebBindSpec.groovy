package geb.bind.spock

import geb.bind.Bind
import geb.spock.GebSpec

abstract class GebBindSpec extends GebSpec {

    Bind start(Class<Bind> bindClass) {
        Bind.start(browser, bindClass)
    }

}
