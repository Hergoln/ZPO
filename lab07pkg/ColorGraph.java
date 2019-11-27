package com.company.lab07pkg;
import org.jacop.core.*;
import org.jacop.constraints.*;
import org.jacop.search.*;

public class ColorGraph
{

    /* Macierz (chyba)
        PL, BR, UA, SL, CZ, AU, HU, GE, DN
      PL[-,  1,  1,  1,  1,  0,  0,  1,  0] < 0
      BR[-,  -,  1,  0,  0,  0,  0,  0,  0] < 1
      UA[-,  -,  -,  1,  0,  0,  1,  0,  0] < 2
      SL[-,  -,  -,  -,  1,  1,  1,  0,  0] < 3
      CZ[-,  -,  -,  -,  -,  1,  0,  1,  0] < 4
      AU[-,  -,  -,  -,  -,  -,  1,  1,  0] < 5
      HU[-,  -,  -,  -,  -,  -,  -,  0,  0] < 6
      GE[-,  -,  -,  -,  -,  -,  -,  -,  1] < 7
      DN[-,  -,  -,  -,  -,  -,  -,  -,  -] < 8
         0   1   2   3   4   5   6   7   8
     */

    public static void drawStuff()
    {
        int size = 9;
        String[] colors = new String[size];
        colors[0] = "czerwony";
        colors[1] = "czarny";
        colors[2] = "niebieski";
        colors[3] = "zielony";
        colors[4] = "żółty";

        Store store = new Store();
        IntVar[] v = new IntVar[size];

        v[0] = new IntVar(store, "Poland", 1, size);
        v[1] = new IntVar(store, "Bielarus", 1, size);
        v[2] = new IntVar(store, "Ukraine", 1, size);
        v[3] = new IntVar(store, "Slovenia", 1, size);
        v[4] = new IntVar(store, "Czech", 1, size);
        v[5] = new IntVar(store, "Austria", 1, size);
        v[6] = new IntVar(store, "Hungary", 1, size);
        v[7] = new IntVar(store, "Germany", 1, size);
        v[8] = new IntVar(store, "Denmark", 1, size);

        store.impose( new XneqY(v[0], v[1]) );
        store.impose( new XneqY(v[0], v[2]) );
        store.impose( new XneqY(v[0], v[3]) );
        store.impose( new XneqY(v[0], v[4]) );

        store.impose( new XneqY(v[0], v[7]) );
        store.impose( new XneqY(v[1], v[2]) );
        store.impose( new XneqY(v[2], v[3]) );
        store.impose( new XneqY(v[2], v[6]) );

        store.impose( new XneqY(v[3], v[4]) );
        store.impose( new XneqY(v[3], v[5]) );
        store.impose( new XneqY(v[3], v[6]) );
        store.impose( new XneqY(v[4], v[5]) );

        store.impose( new XneqY(v[4], v[7]) );
        store.impose( new XneqY(v[5], v[6]) );
        store.impose( new XneqY(v[5], v[7]) );
        store.impose( new XneqY(v[7], v[8]) );


        // search for a solution and print results
        Search<IntVar> search = new DepthFirstSearch<IntVar>();
        SelectChoicePoint<IntVar> select =
                new InputOrderSelect<IntVar>(store, v,
                        new IndomainMin<IntVar>());
        boolean result = search.labeling(store, select);

        if ( result )
            for(int i = 0; i < size; ++i) System.out.println("Solution: " + colors[v[i].value()]);
        else
            System.out.println("*** No");
    }
}
