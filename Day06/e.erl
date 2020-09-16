-module(e).
-compile(export_all).
-import(myio, [read_file/1]).

test() ->
	Map = #{"B" => "COM","C" => "B","D" => "C","E" => "D","F" => "E",
		  "G" => "B","H" => "G","I" => "D","J" => "E","K" => "J",
		    "L" => "K","SAN" => "I","YOU" => "K"},

	Map = orbitMap(["COM)B","B)C","C)D","D)E","E)F","B)G","G)H","D)I","E)J",
 				"J)K","K)L","K)YOU","I)SAN"]),
	54 = orbitChecksum(Map),
	works.

start(Filename) -> orbitChecksum(orbitMap(read_file(Filename))).

orbitChecksum(M) when is_map(M) -> orbitChecksum(M, maps:keys(M)).
orbitChecksum(_M, []) -> 0;
orbitChecksum(M, [F|R]) -> distToOrigin(F,M) + orbitChecksum(M, R).

distToOrigin(Key, M) -> 
	case maps:get(Key, M, null) of
		null -> 0;
		X -> 1 + distToOrigin(X, M)
	end. 

orbitMap([]) -> maps:new();
orbitMap([F|R]) -> 
	[Value, Key] = string:split(F, ")"),
	maps:put(Key, Value, orbitMap(R)).

