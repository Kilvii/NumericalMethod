fun main(){
    val Points = arrayOf(0.0,0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0)
    val Values = arrayOf(2.0,2.105, 2.221, 2.349, 2.491, 2.648, 2.822, 3.013, 3.225, 3.459, 3.718)


    val table = table(Values)

    println("Task 1 :")
//    firstNewton(Points,Values,table,0.1)
//    firstNewton(Points,Values,table,0.8)
//    secondNewton(Points,Values,table,0.1)
//    secondNewton(Points,Values,table,0.8)
//    firstGauss(Points,Values,table,0.1)
    firstGauss(Points,Values,table,0.4)
//    firstGauss(Points,Values,table,0.8)
//    secondGauss(Points,Values,table,0.1)
//    secondGauss(Points,Values,table,0.8)

    println("Task 2 :")
    firstNewton(Points,Values,table,0.453)
    firstNewton(Points,Values,table,0.541)
    secondNewton(Points,Values,table,0.453)
    secondNewton(Points,Values,table,0.541)
    firstGauss(Points,Values,table,0.453)
    firstGauss(Points,Values,table,0.541)
    secondGauss(Points,Values,table,0.453)
    secondGauss(Points,Values,table,0.541)

}
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i
    }
    return result
}
fun table(y : Array<Double>) : Array<Array<Double>> {
    val size = y.size
    val Elements: Array<Array<Double>> = Array(size) { Array(size) { 0.0 } }
    for(i in 0 until size ){
        for(j in 0 until size - i){
            if(i == 0){
                Elements[i][j] = y[j]
                continue
            }
            Elements[i][j] = Elements[i-1][j+1] - Elements[i-1][j]
        }
    }
    return  Elements
}
fun table(y : Array<Int>) : Array<Array<Int>> {
    val size = y.size
    val Elements: Array<Array<Int>> = Array(size) { Array(size) { 0 } }
    for(i in 0 until size ){
        for(j in 0 until size - i){
            if(i == 0){
                Elements[i][j] = y[j]
                continue
            }
            Elements[i][j] = Elements[i-1][j+1] - Elements[i-1][j]
        }
    }
    return  Elements
}
fun firstNewton(x: Array<Double>, y: Array<Double>, table: Array<Array<Double>>, point: Double ){
    val size = y.size
    var numerator = 1.0
    var result = table[0][0]
    for(i in 1 until size ) {
        val factor = factorial(i)
        val h = x[i] - x[i-1]
        val t = (point - x[0])/h
        for (j in 1..i){
            numerator *= t - j + 1
        }
        result += (numerator/factor) * table[i][0]
        numerator = 1.0
    }
    println("First Newton -> The value at the point $point : $result")

}
fun secondNewton(x: Array<Double>, y: Array<Double>, table: Array<Array<Double>>, point: Double ){
    val size = y.size
    var numerator = 1.0
    var result = table[0][size-1]
    var k = 1
    var q = 9
    for(i in 1 until size ) {
        val factor = factorial(i)
        val h = x[i] - x[i-1]
        val t = (point - x[size-1])/h
        for (j in 1..i){
            numerator *= t + j - 1
        }
        result += (numerator/factor) * table[k][q]
        numerator = 1.0
        q--
        k++
    }
    println("Second Newton -> The value at the point $point : $result")

}
fun firstGauss(x: Array<Double>, y: Array<Double>, table: Array<Array<Double>>, point: Double){
    val size = y.size
    var numerator = 1.0
    var k = size / 2
    var v =1
    var q =1

    var result = table[0][(size)/2]
    for(i in 1 until size ) {
        val factor = factorial(i)
        val h = x[i] - x[i-1]
        val t = (point - x[size/2])/h

            if(i%2==0){
                numerator *= (t - v)
                v += 1
            }
            else{
                numerator *= (t + i - v)

            }
        val buffer = (numerator/factor) * table[i][k]
        result += buffer
        q += 1
        if (q == 2){
            k -= 1
            q = 0
        }

    }
    println("First Gauss -> The value at the point $point : $result")
}
fun secondGauss(x: Array<Double>, y: Array<Double>, table: Array<Array<Double>>, point: Double){
    val size = y.size
    var numerator = 1.0
    var k = (size / 2) - 1
    var v = 0
    var q = 0
    var f = 0
    var p = 1

    var result = table[0][(size)/2]
    for(i in 1 until size ) {
        val factor = factorial(i)
        val h = x[i] - x[i-1]
        val t = (point - x[size/2])/h
        if(i<4) {
            if (f < 2) {
                numerator *= (t + i - p)
                f += 1
                v += 1
            } else if (f < 4) {
                numerator *= (t - i + v)
                f += 1
                p += 1

            }
        }
        else{
            if(i%2==0){
                numerator *= (t - v)
                v += 1
            }
            else{
                numerator *= (t + i - v)

            }
        }

        val buffer = (numerator/factor) * table[i][k]
        result += buffer
        //println("Step $i , t = $numerator , prom res = $buffer , res = $result")
        q += 1

        if (q == 2){
            k -= 1
            q = 0
        }
        if(f == 4){
            f = 0
        }

    }
    println("Second Gauss -> The value at the point $point : $result")
}
