package katze.fmc.forge;


import com.google.common.collect.Lists;
import java.util.List;
import java.util.stream.IntStream;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

public class PistonStructureResolver {
    public static final int MAX_PUSH_DEPTH = 12;
    private final Level level;
    private final BlockPos pistonPos;
    private final boolean extending;
    private final BlockPos startPos;
    private final Direction pushDirection;
    private final List<BlockPos> toPush = Lists.newArrayList();
    private final Direction pistonDirection;

    public PistonStructureResolver(Level p_60418_, BlockPos p_60419_, Direction p_60420_, boolean p_60421_) {
        this.level = p_60418_;
        this.pistonPos = p_60419_;
        this.pistonDirection = p_60420_;
        this.extending = p_60421_;
        if (p_60421_) {
            this.pushDirection = p_60420_;
            this.startPos = p_60419_.relative(p_60420_);
        } else {
            this.pushDirection = p_60420_.getOpposite();
            this.startPos = p_60419_.relative(p_60420_, 2);
        }
    }

    public boolean resolve() {
        this.toPush.clear();
        BlockState blockstate = this.level.getBlockState(this.startPos);
        if (PistonBaseBlock.isPushable(blockstate, this.level, this.startPos, this.pushDirection, false, this.pistonDirection)) {
            if (this.addBlockLine(this.startPos, this.pushDirection)) {
                for (BlockPos blockpos : this.toPush) {
                    if (this.level.getBlockState(blockpos).isStickyBlock() && !this.addBranchingBlocks(blockpos)) {
                        return false;
                    }
                }

                return true;
            } else {
                return false;
            }
        } else if (this.extending && blockstate.getPistonPushReaction() == PushReaction.DESTROY) {
            return true;
        } else {
            return false;
        }
    }

    private boolean addBlockLine(BlockPos targetPos, Direction dir) {
        BlockState targetState = this.level.getBlockState(targetPos);
        if (level.isEmptyBlock(targetPos)) {
            return true;
        } else if (!PistonBaseBlock.isPushable(targetState, this.level, targetPos, this.pushDirection, false, dir)) {
            return true;
        } else if (targetPos.equals(this.pistonPos)) {
            return true;
        } else if (this.toPush.contains(targetPos)) {
            return true;
        } else {
            if (1 + this.toPush.size() > 12) {
                return false;
            } else {
                int havePushed = 1;
                BlockState currentState;
                while(targetState.isStickyBlock()) {
                    BlockPos blockpos = targetPos.relative(this.pushDirection.getOpposite(), havePushed);
                    currentState = targetState;
                    targetState = this.level.getBlockState(blockpos);
                    if (targetState.isAir() 
                            || !(currentState.canStickTo(targetState) && targetState.canStickTo(currentState)) 
                            || !PistonBaseBlock.isPushable(targetState, this.level, blockpos, this.pushDirection, false, this.pushDirection.getOpposite())
                            || blockpos.equals(this.pistonPos)) {
                        break;
                    }

                    ++havePushed;
                    if (havePushed + this.toPush.size() > 12) {
                        return false;
                    }
                }

                int l = 0;

                for(int index = havePushed - 1; index >= 0; --index) {
                    this.toPush.add(targetPos.relative(this.pushDirection.getOpposite(), index));
                    ++l;
                }

                int j1 = 1;

                while(true) {
                    BlockPos currentPos = targetPos.relative(this.pushDirection, j1);
                    int posIndex = this.toPush.indexOf(currentPos);
                    if (posIndex > -1) {
                        this.reorderListAtCollision(l, posIndex);

                        return IntStream.rangeClosed(0, posIndex + l).mapToObj(this.toPush::get).noneMatch(blockpos2 -> this.level.getBlockState(blockpos2).isStickyBlock() && !this.addBranchingBlocks(blockpos2));
                    }

                    targetState = this.level.getBlockState(currentPos);
                    if (targetState.isAir()) {
                        return true;
                    }

                    if (!PistonBaseBlock.isPushable(targetState, this.level, currentPos, this.pushDirection, true, this.pushDirection) || currentPos.equals(this.pistonPos)) {
                        return false;
                    }

                    if (targetState.getPistonPushReaction() == PushReaction.DESTROY) {
                        return true;
                    }

                    if (this.toPush.size() >= 12) {
                        return false;
                    }

                    this.toPush.add(currentPos);
                    ++l;
                    ++j1;
                }
            }
        }
    }

    private void reorderListAtCollision(int p_60424_, int p_60425_) {
        List<BlockPos> list = Lists.newArrayList();
        List<BlockPos> list1 = Lists.newArrayList();
        List<BlockPos> list2 = Lists.newArrayList();
        list.addAll(this.toPush.subList(0, p_60425_));
        list1.addAll(this.toPush.subList(this.toPush.size() - p_60424_, this.toPush.size()));
        list2.addAll(this.toPush.subList(p_60425_, this.toPush.size() - p_60424_));
        this.toPush.clear();
        this.toPush.addAll(list);
        this.toPush.addAll(list1);
        this.toPush.addAll(list2);
    }

    private boolean addBranchingBlocks(BlockPos p_60432_) {
        BlockState blockstate = this.level.getBlockState(p_60432_);

        for(Direction direction : Direction.values()) {
            if (direction.getAxis() != this.pushDirection.getAxis()) {
                BlockPos blockpos = p_60432_.relative(direction);
                BlockState blockstate1 = this.level.getBlockState(blockpos);
                if (blockstate1.canStickTo(blockstate) && blockstate.canStickTo(blockstate1) && !this.addBlockLine(blockpos, direction)) {
                    return false;
                }
            }
        }

        return true;
    }
}
