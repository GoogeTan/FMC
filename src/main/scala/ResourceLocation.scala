package fmc

import syntax.all.{*, given}

final case class ResourceLocation(namespace : String :| ResourceNamespace, path : String :| ResourcePath)

type ResourcePath = Match["([a-z]|[A-Z]|_|-|\\/)*"]
type ResourceNamespace = Match["([a-z]|[A-Z]|-)*"]
