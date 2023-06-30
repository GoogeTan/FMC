package me.zahara.fmc
package block

import collection.TypeErasureDependentMap


type Properties = TypeErasureDependentMap[Property]

def noProperties : Properties = TypeErasureDependentMap.empty