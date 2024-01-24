package katze.fmc

import katze.fmc.ModId

trait Mod[F[_]]:
  val modId : ModId
  val initCommon : F[Unit]
  val initClient : F[Unit]
  val initServer : F[Unit]