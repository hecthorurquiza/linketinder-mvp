import aczg.groovy.linketinder.Menu
import aczg.groovy.linketinder.Persistence

static void main(String[] args) {
  Menu menu = new Menu(new Persistence())
  menu.showMenu()
}