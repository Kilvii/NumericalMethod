import kotlin.math.pow

fun  main(){
    val points = arrayOf(0.0,0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0)
    val values = arrayOf(2.0,2.105, 2.221, 2.349, 2.491, 2.648, 2.822, 3.013, 3.225, 3.459, 3.718)

    val N = values.size
    val koefC = tridiagonalMatrixAlgorithm(values,11)
    val koefA = Array(values.size,{0.0})
    val koefB = Array(values.size,{0.0})
    val koefD = Array(values.size,{0.0})

    for (i in 1 .. values.size-1){
        val h = points[i] - points[i-1]
        koefD[i] = (koefC[i] - koefC[i-1]) / h
        koefA[i] = values[i]
        koefB[i] = (h/2)*koefC[i] - ((h*h)/6)*koefD[i] + (values[i] - values[i-1])/h
    }
    koefA[0] = values[0]

    val resX = Array(values.size * 10,{0.0})
    val resY = Array(values.size * 10,{0.0})

    var c = 0
    for (i in 0 until N-1){
        for (j in 0 until N){
            if(j == 0){
                resX[i*10+i] = points[i]
            }
            else{
                resX[i*10+j + c] = resX[(i*10+j + c) - 1] + 0.01
            }

            resY[i*10+j + c] = koefA[i]
            + koefB[i]*(resX[i*10+j + c] - resX[i*10])
            + (koefC[i]/2)* (resX[i * 10 + j + c] - resX[i * 10]).pow(2.0)
            + (koefD[i]/6)* (resX[i * 10 + j + c] - resX[i * 10]).pow(3.0)
            if( i == 0 && j>0){
                resY[j] += resX[j]
            }
        }
        c += 1
    }

    for(i in resY.indices){
        print("${resX[i]} ")
    }
    println()
    for(element in resY){
        print("$element ")
    }
}

fun tridiagonalMatrixAlgorithm(y:Array<Double>,N:Int) :Array<Double>{
    val h = 0.1
    var buf: Double
    val beta = Array(N,{0.0})
    val alpha = Array(N,{0.0})
    val c = Array(N,{0.0})
    val F = Array(N,{0.0})
    val A: Array<Array<Double>> = Array(N) { Array(N) { 0.0 } }
    A[0][0] = 1.0
    A[0][1] = h
    F[0] = 0.0
    for (i in 1 until N - 1) {
        A[i][i - 1] = h
        A[i][i] = 4*h
        A[i][i + 1] = h

        F[i] = 6*((y[i+1]-y[i])/h - (y[i]-y[i-1])/h)
    }
    A[N - 1][N - 2] = h
    A[N - 1][N - 1] = 1.0
    F[N-1] = 0.0

//    for (i in 0 until N){
//        for(j in 0 until N){
//            print("${A[i][j]} ")
//        }
//        println()
//    }

    for (j in 0 until N){
        if(j==0){
            buf = A[j][j]
            alpha[j] = -A[j][1] / buf
            beta[j] = F[j]/buf
            continue
        }
        for (i in 1 until N-1){
            buf = A[i][i] + A[i][i-1] * alpha[i-1]
            alpha[i] = -A[i][i+1]/buf
            beta[i] = (F[i]-A[i][i-1]*beta[i-1])/buf
        }
        if(j == N-1){
            buf = A[j][j] + A[j][j-1]*alpha[j-1]
            beta[j] = (F[j]-A[j][j-1]*beta[j-1])/buf
        }
    }

    for(i in N-1 downTo 0){
        if(i == N-1){
            c[i] = beta[i]
            continue
        }
        c[i] = alpha[i] * c[i+1] + beta[i]
    }

    c[0] = 0.0
    c[N-1] = 0.0

    return c
}
