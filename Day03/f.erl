-module(f).
-compile(export_all).
%-export([test/1,start/1, wires/2]).

test() ->
	{13,0} = pos({3,0}, "R10"),
	{-7,0} = pos({3,0}, "L10"),
	{3, 16} = pos({3,9}, "U7"),
	{3, -200} = pos({3, 9}, "D209"),

	{ok, {3,5}} = cross({5, 5}, {1,5}, {3, 9}, {3, 0}),
	{ok, {6,2}} = cross({6, 0}, {6, 4}, {8, 2}, {2, 2}),
	{none, null} = cross({3, 2}, {3, 5}, {6, 4}, {10, 4}),

	2 = getDistFromPoints({3,5}, {2, 6}, {3, 6}),

	30 = wires(["R8","U5","L5","D3"], ["U7","R6","D4","L4"]),
	610 = wires(["R75","D30","R83","U83","L12","D49","R71","U7","L72"], ["U62","R66","U55","R34","D71","R55","D58","R83"]),
	410 = wires(["R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51"], ["U98","R91","D20","R16","D67","R40","U7","R15","U6","R7"]),
	works.

start(Filename) -> 
	[A,B] = myio:read_file(Filename),
	wires(string:split(A, ",", all), string:split(B, ",", all)).

wires(A,B) -> wires(A,B,{0,0}, [], 0).
wires([],_B,_Pos,R,_Dist) -> lists:min(lists:filter(fun isZero/1, R));
wires([A|Al],B,Pos,R,Dist) -> wires(Al, B, pos(Pos, A), wires_b(Pos, pos(Pos, A), {0,0}, B, R, Dist), dist(A) + Dist).

wires_b(_A1, _A2, _B1, [], R, _Dist) -> R;
wires_b(A1, A2, B1, [B|Bl], R, Dist) ->  
	B2 = pos(B1, B),
	case cross(A1, A2, B1, B2) of 
		{ok, P} -> wires_b(A1,A2,B2, Bl, [Dist + getDistFromPoints(A1,B1,P) | R], Dist + dist(B));
		{none, null} -> wires_b(A1,A2,B2,Bl,R, Dist + dist(B))
	end.

cross ({AX, AY1}, {AX,AY2}, B1, B2) when AY1 > AY2 -> cross({AX, AY2}, {AX, AY1}, B1, B2);
cross ({AX1, AY}, {AX2,AY}, B1, B2) when AX1 > AX2 -> cross({AX2, AY}, {AX1, AY}, B1, B2);
cross (A1, A2, {BX1, BY}, {BX2, BY}) when BX1 > BX2 -> cross(A1, A2, {BX2, BY}, {BX1, BY});
cross (A1, A2, {BX, BY1}, {BX, BY2}) when BY1 > BY2 -> cross(A1, A2, {BX, BY2}, {BX, BY1});
cross({AX, AY1}, {AX, AY2}, {BX1, BY}, {BX2, BY}) when AY1 < AY2, BX1 < BX2 ->
       if BX1 =< AX, AX =< BX2, AY1 =< BY, BY =< AY2 ->
		  {ok, {AX, BY}};
	  true -> 
		  {none, null}
       end;
cross({AX1, AY}, {AX2, AY}, {BX, BY1}, {BX, BY2}) when AX1 < AX2, BY1 < BY2 ->
       if AX1 =< BX, BX =< AX2, BY1 =< AY, AY =< BY2 ->
		  {ok, {BX, AY}};
	  true -> 
		  {none,null}
       end;
cross(_A1, _A2, _B1, _B2) -> {none, null}.

getDistFromPoints({X, AY}, {BX, Y}, {X,Y}) -> abs(abs(BX) - abs(X))  + abs(abs(AY) - abs(Y));
getDistFromPoints({AX, Y}, {X, BY}, {X,Y}) -> abs(abs(AX) - abs(X))  + abs(abs(BY) - abs(Y)).

dist(Str) ->
	{Steps, _} = string:to_integer(string:substr(Str, 2)),
	Steps.

direction(Str) -> 
	Dir = string:substr(Str, 1, 1),
	{Dir,dist(Str)}.

pos({X,Y}, {Dir, Steps}) ->
	case Dir of
		"R" -> {X+Steps, Y};
		"L" -> {X-Steps, Y};
		"U" -> {X,Y+Steps};
		"D" -> {X,Y-Steps}
	end;
pos(P, Str) -> pos(P,direction(Str)).

isZero(0) -> false;
isZero(_V) -> true.
