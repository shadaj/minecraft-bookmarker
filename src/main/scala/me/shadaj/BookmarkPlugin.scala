package me.shadaj

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.Location
import org.bukkit.entity.Player

class BookmarkPlugin extends ScalaPlugin {
  val bookmarks = collection.mutable.Map[String, Location]()

  override def onCommand(sender: CommandSender, cmd: Command, label: String, args: Array[String]): Boolean = {
    val player = sender.asInstanceOf[Player]
    if (cmd.getName() == "bookmark") {
      val currentLocation = player.getLocation()
      val name = args(0)

      bookmarks += name -> currentLocation
      
      true
    } else if (cmd.getName() == "goTo") {
      val toGo = bookmarks(args(0))
      player.teleport(toGo)
      true
    } else if (cmd.getName() == "listPlaces") {
      bookmarks.keys.foreach(player.sendMessage)
      true
    } else {
      false
    }
  }
}