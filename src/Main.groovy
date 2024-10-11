import aczg.groovy.linketinder.Menu
import aczg.groovy.linketinder.repository.DatabaseConn
import groovy.sql.Sql

static void main(String[] args) {
  Sql connection = DatabaseConn.newInstance()
  Menu menu = new Menu(connection)
  menu.showMenu()
}