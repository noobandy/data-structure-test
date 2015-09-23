/**
 * Created by Anand on 9/23/2015.
 */
public class Explorer {

    public static int explore(int[] input1, int[] input2) {
        int paths = 0;
        int m = input1[0];
        int n = input1[1];

        int[] pendingMoves = new int[2 * n];
        int pendingMovesTop = -1;
        int position = 0;
        boolean allMovesExplored = false;

        do {
            int nextRight = (position / n) * n + (position % n ) + 1 < (m * n) ? (position / n) * n + (position % n ) + 1 : -1;
            int nextDown = ((position / n) + 1) * n + (position % n ) < (m * n) ? ((position / n) + 1) * n + (position % n ) : -1;
            int nextDiagonally = ((position / n) + 1) * n + (position % n ) + 1 < (m * n) ? ((position / n) + 1) * n + (position % n ) + 1 : -1;

            int num = input2[position];

            switch (num) {
                case 0 :
                    //no moves
                    if(position == (m * n) - 1) {
                        paths = paths + 1;
                    }
                    //try next pending move
                    if(pendingMovesTop >= 0) {
                        position = pendingMoves[pendingMovesTop];
                        pendingMovesTop--;
                    } else {
                        allMovesExplored = true;
                    }
                    break;
                case 1:
                    //move right
                    if(nextRight == -1) {
                        if(pendingMovesTop >= 0) {
                            position = pendingMoves[pendingMovesTop];
                            pendingMovesTop--;
                        } else {
                            allMovesExplored = true;
                        }
                    } else {
                        position = nextRight;
                    }

                    break;
                case 2:
                    //move down
                    if(nextDown == -1) {
                        if(pendingMovesTop >= 0) {
                            position = pendingMoves[pendingMovesTop--];

                        } else {
                            allMovesExplored = true;
                        }
                    } else {
                        position = nextDown;
                    }
                    break;
                case 3:
                    //move diagonally
                    if(nextDiagonally == -1) {
                        if(pendingMovesTop >= 0) {
                            position = pendingMoves[pendingMovesTop--];

                        } else {
                            allMovesExplored = true;
                        }
                    } else {
                        position = nextDiagonally;
                    }
                    break;
                case 4:
                    //move right and down
                   if(nextRight == -1) {
                       if(nextDown == -1) {
                           if(pendingMovesTop >= 0) {
                               position = pendingMoves[pendingMovesTop--];

                           } else {
                               allMovesExplored = true;
                           }
                       } else {
                           position = nextDown;
                       }
                   } else {
                       position = nextRight;
                       if(nextDown != -1) {
                          pendingMoves[++pendingMovesTop] = nextDown;
                       }
                   }
                    break;
                case 5 :
                    //move right and diagonally
                    if(nextRight == -1) {
                        if(nextDiagonally == -1) {
                            if(pendingMovesTop >= 0) {
                                position = pendingMoves[pendingMovesTop--];

                            } else {
                                allMovesExplored = true;
                            }
                        } else {
                            position = nextDiagonally;
                        }
                    } else {
                        position = nextRight;
                        if(nextDiagonally != -1) {
                            pendingMoves[++pendingMovesTop] = nextDiagonally;
                        }
                    }
                    break;
                case 6:
                    //move down and diagonally
                    if(nextDown == -1) {
                        if(nextDiagonally == -1) {
                            if(pendingMovesTop >= 0) {
                                position = pendingMoves[pendingMovesTop--];

                            } else {
                                allMovesExplored = true;
                            }
                        } else {
                            position = nextDiagonally;
                        }
                    } else {
                        position = nextDown;
                        if(nextDiagonally != -1) {
                            pendingMoves[++pendingMovesTop] = nextDiagonally;
                        }
                    }
                    break;
                case 7:
                    //move right , down and diagonally
                    if(nextRight == -1) {
                        if(nextDown == -1) {
                            if(nextDiagonally == -1) {
                                if(pendingMovesTop >= 0) {
                                    position = pendingMoves[pendingMovesTop--];

                                } else {
                                    allMovesExplored = true;
                                }
                            } else {
                                position = nextDiagonally;
                                if(nextRight != -1) {
                                    pendingMoves[++pendingMovesTop] = nextRight;
                                }

                                if(nextDown != -1) {
                                    pendingMoves[++pendingMovesTop] = nextDown;
                                }
                            }

                        } else {
                            position = nextDown;
                            if(nextRight != -1) {
                                pendingMoves[++pendingMovesTop] = nextRight;
                            }

                            if(nextDiagonally != -1) {
                                pendingMoves[++pendingMovesTop] = nextDiagonally;
                            }
                        }
                    } else {
                        position = nextRight;
                        if(nextDown != -1) {
                            pendingMoves[++pendingMovesTop] = nextDown;
                        }

                        if(nextDiagonally != -1) {
                            pendingMoves[++pendingMovesTop] = nextDiagonally;
                        }
                    }
                    break;
                default:
                    break;
            }


        } while(!allMovesExplored);


        return paths;
    }

    public static void main(String[] args) {

        System.out.println(Explorer.explore(new int[]{4,6}, new int[] {1,3,0,0,0,0,0,0,4,5,1,0,0,0,0,6,7,6,0,0,0,0,5,0}));

    }
}


