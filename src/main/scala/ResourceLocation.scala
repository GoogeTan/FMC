package me.zahara.fmc

import syntax.all.{*, given}
import io.github.iltotore.iron.*

case class ResourceLocation(namespace : String :| ResourceNamespace, path : String :| ResourcePath)

type ResourcePath = Match["([a-z]|[A-Z]|_|-|\\/)*"]
type ResourceNamespace = Match["([a-z]|[A-Z]|-)*"]
