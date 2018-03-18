class  TestExample(implicit p: Parameters) extends LazyRoCC {
  override lazy val module = new TestExampleModule(this)
}

class TestExampleModule(outer: TestExample)(implicit p: Parameters) extends LazyRoCCModule(outer) with HasCoreParameters {
	
    val s_idle :: s_resp :: Nil = Enum(Bits(), 2)
    val state = Reg(init = s_idle)
    
	//val a = Reg(UInt(width = xLen))
	//val b = Reg(UInt(width = xLen))
	val sum = Reg(UInt(width = xLen))
    
    val resp_rd = Reg(io.resp.bits.rd)
    
    io.cmd.ready := (state === s_idle)
    io.resp.valid := (state === s_resp)
    
    when (io.cmd.fire()) {
		//a := io.cmd.bits.rs1
		//b := io.cmd.bits.rs2
		sum := io.cmd.bits.rs1 + io.cmd.bits.rs2
        resp_rd := io.cmd.bits.inst.rd
        state := s_resp
    }
    
    when (io.resp.fire()) {
        state := s_idle
    }
    
    io.resp.bits.rd := resp_rd
    io.resp.bits.data := sum
    io.busy := (state =/= s_idle)
    io.interrupt := Bool(false)
	io.mem.req.valid := Bool(false)
	
}
