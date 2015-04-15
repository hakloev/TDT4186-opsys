

# Operativsystemer øving P3
Fredrik Berg, Håkon Løvdal, Truls Mørk Pettersen

### Logg av programkjøring, eksempel på statestikk

	Simulation statistics:
	
	Number of completed processes:                                239
	Number of created processes:                                  239
	Number of (forced) process switches:                          235
	Number of processed I/O operations:                           354
	Average throughput (processes per second):                    0.956
	
	Total CPU time spent processing:                              229256.0 ms
	Fraction of CPU time spent processing:                        91.7024 %
	Total CPU time spent waiting:                                 20744.0
	Fraction of CPU time spent waiting:                           8.2976 %
	
	Largest occuring memory queue length:                         6
	Average memory queue length:                                  0.965448
	Largest occuring CPU queue length:                            8
	Average CPU queue length:                                     2.68832
	Largest occuring I/O queue length:                            4
	Average I/O queue length:                                     0.14432
	Average # of times a process has been placed in memory queue: 1.0
	Average # of times a process has been placed in CPU queue:    3.464435
	Average # of times a process has been placed in I/O queue:    1.4811715
	
	Average time spent in system per process:                     5274.661 ms
	Average time spent waiting for memory per process:            1009 ms
	Average time spent waiting for CPU per process:               2812.0503 ms
	Average time spent processing per process:                    959.2301 ms
	Average time spent waiting for I/O per process:               150.96234 ms
	Average time spent in I/O per process:                        338.48535 ms

### Implementasjon

Det er ikke gjort noen spesielle endringer i skjelletkoden som ble gitt for øvingen. Simulator har blitt utvidet og vi har implementert to klasser, IO og CPU.

**Simulator**


**CPU**


**IO**



### Manipulering av forskjellige parametere

#### Eksperimentering med round robin algoritme
Ved manipulering og endring av tiden round robin algoritmen gir prosessene ser vi at ved lavere verdier som 200ms vil CPU bruke mye av tiden sin på å vente og ikke prosessere.
Nærmere alle prosessene som kommer inn blir prosessesert ved en lavere verdi. For høye verdier jobber CPU oftere og en høyere andel tid er dermed brukt på prosessering.
Ved høyere verdier for round robin algoritmer blir også køene vesentlig større. Endringen proganeres videre i systemet og fører til at prosessene står lengre i køer å venter.
Det gjelder å finne en verdi som gir en god kombinasjon av avbrudd og prosesseringstid som gir størst gjennomstrøm av prosesser.


#### Endring av IO prosseseringstid
En endring i IO prossesering gjør at det oftere er prosesser som blir lagt i IO køen. Gjennomsnittlig størrelse på IO køen øker.
Ved å minske prosseseringstiden går prosessene fortere gjennom IO og blir raskere lagt tilbake i CPU køen, noe som øker hastigheten i systemet.


#### Endring av CPU prosseseringstid



#### Endring i minnestørrelse
Med en større minnestørrelse kan vi putte flere prosesser inn i minnet om gangen. Problemet er at vi risikerer at minnet fylles opp og blir veldig stort.
Større minne kan derimot gi fordeler ved at vi har plass til mange små prosesseser med mye minnekrav. 