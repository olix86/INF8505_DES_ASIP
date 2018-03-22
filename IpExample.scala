class  IpExample(implicit p: Parameters) extends LazyRoCC {
  override lazy val module = new IpExampleModule(this)
}

class IpExampleModule(outer: IpExample)(implicit p: Parameters) extends LazyRoCCModule(outer)
with HasCoreParameters {
	
    val s_idle :: s_resp :: Nil = Enum(Bits(), 2)
    val state = Reg(init = s_idle)
    
	val output = Reg(UInt(width = xLen))
	
    val resp_rd = Reg(io.resp.bits.rd)
    
    io.cmd.ready := (state === s_idle)
    io.resp.valid := (state === s_resp)
    
    val a = io.cmd.bits.rs1
    val x = Cat(a(7-1), a(15-1), a(23-1), a(31-1), a(39-1), a(47-1), a(55-1), a(63-1),
				a(5-1), a(13-1), a(21-1), a(29-1), a(37-1), a(45-1), a(53-1), a(61-1),
				a(3-1), a(11-1), a(19-1), a(27-1), a(35-1), a(43-1), a(51-1), a(59-1),
				a(1-1), a(9-1), a(17-1), a(25-1), a(33-1), a(41-1), a(49-1), a(57-1),
				a(8-1), a(16-1), a(24-1), a(32-1), a(40-1), a(48-1), a(56-1),  a(64-1),
				a(6-1), a(14-1), a(22-1), a(30-1), a(38-1), a(46-1), a(54-1), a(62-1),
				a(4-1), a(12-1), a(20-1), a(28-1), a(36-1), a(44-1), a(52-1), a(60-1),
				a(2-1), a(10-1), a(18-1), a(26-1), a(34-1), a(42-1), a(50-1), a(58-1))
    
    when (io.cmd.fire()) {
		output := x
        resp_rd := io.cmd.bits.inst.rd
        state := s_resp
    }
    
    when (io.resp.fire()) {
        state := s_idle
    }
    
    io.resp.bits.rd := resp_rd
    io.resp.bits.data := output
    io.busy := (state =/= s_idle)
    io.interrupt := Bool(false)
	io.mem.req.valid := Bool(false)
	
}
