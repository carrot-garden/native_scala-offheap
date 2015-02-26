package offheap
package internal

import sun.misc.Unsafe

object UnsafeMemory extends Memory {
  private val unsafe: Unsafe =
    try Unsafe.getUnsafe()
    catch {
      case _: SecurityException =>
        try {
          val theUnsafe = classOf[Unsafe].getDeclaredField("theUnsafe")
          theUnsafe.setAccessible(true)
          theUnsafe.get(null).asInstanceOf[Unsafe]
        } catch {
          case _: Exception => null
        }
    }

  def allocateMemory(size: Size): Addr           = unsafe.allocateMemory(size)
  def getChar(addr: Addr): Char                  = unsafe.getChar(addr)
  def getByte(addr: Addr): Byte                  = unsafe.getByte(addr)
  def getShort(addr: Addr): Short                = unsafe.getShort(addr)
  def getInt(addr: Addr): Int                    = unsafe.getInt(addr)
  def getLong(addr: Addr): Long                  = unsafe.getLong(addr)
  def getFloat(addr: Addr): Float                = unsafe.getFloat(addr)
  def getDouble(addr: Addr): Double              = unsafe.getDouble(addr)
  def putChar(addr: Addr, value: Char): Unit     = unsafe.putChar(addr, value)
  def putByte(addr: Addr, value: Byte): Unit     = unsafe.putByte(addr, value)
  def putShort(addr: Addr, value: Short): Unit   = unsafe.putShort(addr, value)
  def putInt(addr: Addr, value: Int): Unit       = unsafe.putInt(addr, value)
  def putLong(addr: Addr, value: Long): Unit     = unsafe.putLong(addr, value)
  def putFloat(addr: Addr, value: Float): Unit   = unsafe.putFloat(addr, value)
  def putDouble(addr: Addr, value: Double): Unit = unsafe.putDouble(addr, value)
}
