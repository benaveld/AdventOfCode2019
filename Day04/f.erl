-module(f).
-compile(export_all).

test() ->
	false = validPassword(111111),
	false = validPassword(223450),
	false = validPassword(123789),
	true = validPassword(122345),
	false = validPassword(111123),
	true = validPassword(112233),
	false = validPassword(123444),
	true = validPassword(111122),
	works.

start() -> range(271973, 785961).

range(From, To) when From >= To -> 0;
range(From, To) when From < To -> 
	case validPassword(From) of
		true -> range(From+1, To) + 1;
		false -> range(From+1, To)
	end.

validPassword(Nr) when is_integer(Nr) -> validPassword(listNr(Nr));
validPassword([N|L]) when is_list(L) -> validPassword(N, L, false, 1).

validPassword(_Last, [], _Double, 2) -> true;
validPassword(_Last, [], Double, _Con) -> Double;
validPassword(Last,[Last|Rest], Double, Con) -> validPassword(Last, Rest, Double, Con+1);
validPassword(Last, [Nr|Rest], _Double, 2) when Last < Nr -> validPassword(Nr, Rest,true, 1);
validPassword(Last, [Nr|Rest], Double, _Con) when Last < Nr -> validPassword(Nr, Rest, Double, 1);
validPassword(_Last, _L, _D, _C) -> false.

listNr(Input) -> listNr(Input, trunc(math:log10(Input))).
listNr(Input, 0) -> [getDiget(Input,0)];
listNr(Input, N) -> [getDiget(Input,N) | listNr(Input, N-1)].

getDiget(Input, 0) -> Input rem pow(10, 1);
getDiget(Input, N) when N > 0 -> trunc((Input rem pow(10, N+1)) / math:pow(10, N)).
pow(Base, Ex) -> trunc(math:pow(Base,Ex)).
