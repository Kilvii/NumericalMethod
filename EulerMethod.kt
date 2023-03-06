import kotlin.math.exp
import kotlin.math.pow

fun main(args: Array<String>) {
    val h1 = 0.1
    val h2 = 0.01
    val h3 = 0.001


    val euler1 = explicitEulerOne(2.0,2.0,h1)
    val euler2 = correctEuler(2.0,h1)
    val euler3 = implicitEulerOne(2.0,2.0,h1)

    for (i in 0 until euler1.size){
//        println("y${i}: Явный= ${euler1[i]} Неявный= ${euler3[i]} Точное= ${euler2[i]}")
        println("${euler2[i]}")
    }

}
fun explicitEulerOne(xN:Double,y0:Double, h:Double):List<Double>{
    val N = xN/h
    var buuf:Double
    var xNext = 0.0
    val yS = mutableListOf<Double>()
    yS.add(y0)

    for(i in 1 .. N.toInt()){
        xNext += h
        buuf = yS[i-1] + h * current(xNext,yS[i-1])
        yS.add(buuf)
    }
    return yS
}
fun correctEuler(xN:Double,h:Double):List<Double>{
    val N = xN/h
    var xNext = 0.0
    val yT = mutableListOf<Double>()
//    yT.add(y0)
    for (i in 0 .. N.toInt()){
        var buff = exact(xNext)
        xNext += h
        yT.add(buff)
    }
    return yT
}
fun implicitEulerOne(xN:Double,y0:Double, h:Double):List<Double>{
    val N = xN/h
    var buuf:Double
    var xNext = 0.0
    val yS = mutableListOf<Double>()
    yS.add(y0)

    for(i in 1 .. N.toInt()){
        xNext += h
//        buuf = (1 / (1 - (3 * h / 2))) * (((xNext * xNext) + ((xNext * xNext + 2 * xNext * h + h * h) + 3 * yS[i - 1]) / 2) * h + yS[i - 1])
        buuf = ((xNext.pow(2)+3*yS[i-1]+(xNext+h).pow(2))*h + 2*yS[i-1])/(2-3*h)
        yS.add(buuf)
    }
    return yS
}

fun exact (x:Double): Double {
    return (56/27)*exp(3*x)-(1/3)*x.pow(2)-(2/9)*x-(2/27)
}
fun current(x:Double, y:Double): Double {
    return x.pow(2)+3*y
}
