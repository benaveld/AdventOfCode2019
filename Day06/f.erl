-module(f).
-compile(export_all).
-import(myio, [read_file/1]).

test() ->
	[["E","A","B","C"],["F","A","B","C"]] = addToPath(["E", "F"], ["A","B","C"]),
	["SAN","I","D","E","J","K","YOU"] = orbitPath("YOU", "SAN", orbitMap(read_file("test_data.txt"))),
	works.

test_file() -> 	orbitPath("YOU", "SAN", orbitMap(read_file("test_data.txt"))).

start() -> orbitPath("YOU", "SAN", orbitMap(read_file("day06_data.txt"))).

orbitPath(From, To, Map) -> lists:reverse(orbitPathLoop([[From]], To, Map)).

orbitPathLoop([[To|R]|_], To, _Map) -> [To|R];
orbitPathLoop([[F]], To, Map) -> orbitPathLoop(addToPath(maps:get(F, Map), [F]), To, Map);
orbitPathLoop([F|R], To, Map) ->
       [Node,Con|_] = F,
	Paths = addToPath(getConnections(Node,Con,Map), F),
	orbitPathLoop(lists:append(R,Paths), To, Map).

addToPath([], _Path) -> [];
addToPath([E|R],Path) -> [[E|Path]|addToPath(R,Path)]. 

getConnections(Node, Exclude, Map) -> lists:delete(Exclude, maps:get(Node, Map)). 

orbitMap([]) -> maps:new();
orbitMap([F|R]) -> 
	Map = orbitMap(R),
	[Value, Key] = string:split(F, ")"),
	M = putIntoList(Key, Value, Map),
	putIntoList(Value, Key, M).


putIntoList(Key, Value, Map) when is_map(Map) -> 
	L = [Value | maps:get(Key, Map, [])],
	maps:put(Key, L, Map).
