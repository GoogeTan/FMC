package katze.fmc

import katze.fmc.syntax.all.{ *, given }

final case class ResourceLocation(namespace : String :| ResourceNamespace, path : String :| ResourcePath)

type ResourcePath = Match["([a-z]|[A-Z]|_|-|\\/)*"]
type ResourceNamespace = Match["([a-z]|[A-Z]|-)*"]
type ModId = String :| ResourceNamespace
type Path = String :| ResourcePath