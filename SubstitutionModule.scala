class  SubstitutionExample(implicit p: Parameters) extends LazyRoCC {
  override lazy val module = new SubstitutionExampleModule(this)
}

class SubstitutionExampleModule(outer: SubstitutionExample)(implicit p: Parameters) extends LazyRoCCModule(outer)
with HasCoreParameters {
	
    val s_idle :: s_resp :: Nil = Enum(Bits(), 2)
    val state = Reg(init = s_idle)
    
	val output = Reg(UInt(width = xLen))
	
    val resp_rd = Reg(io.resp.bits.rd)
    
    io.cmd.ready := (state === s_idle)
    io.resp.valid := (state === s_resp)
    
    
	val s1_row0 = Vec(UInt(14),UInt(4),UInt(13),UInt(1),UInt(2),UInt(15),UInt(11),UInt(8),UInt(3),UInt(10),UInt(6),UInt(12),UInt(5),UInt(9),UInt(0),UInt(7))
	val s1_row1 = Vec(UInt(0),UInt(15),UInt(7),UInt(4),UInt(14),UInt(2),UInt(13),UInt(1),UInt(10),UInt(6),UInt(12),UInt(11),UInt(9),UInt(5),UInt(3),UInt(8))
	val s1_row2 = Vec(UInt(4),UInt(1),UInt(14),UInt(8),UInt(13),UInt(6),UInt(2),UInt(11),UInt(15),UInt(12),UInt(9),UInt(7),UInt(3),UInt(10),UInt(5),UInt(0))
    val s1_row3 = Vec(UInt(15),UInt(12),UInt(8),UInt(2),UInt(4),UInt(9),UInt(1),UInt(7),UInt(5),UInt(11),UInt(3),UInt(14),UInt(10),UInt(0),UInt(6),UInt(13))
    
    val s2_row0 = Vec(UInt(15),UInt(1),UInt(8),UInt(14),UInt(6),UInt(11),UInt(3),UInt(4),UInt(9),UInt(7),UInt(2),UInt(13),UInt(12),UInt(0),UInt(5),UInt(10))
	val s2_row1 = Vec(UInt(3),UInt(13),UInt(4),UInt(7),UInt(15),UInt(2),UInt(8),UInt(14),UInt(12),UInt(0),UInt(1),UInt(10),UInt(6),UInt(9),UInt(11),UInt(5))
	val s2_row2 = Vec(UInt(0),UInt(14),UInt(7),UInt(11),UInt(10),UInt(4),UInt(13),UInt(1),UInt(5),UInt(8),UInt(12),UInt(6),UInt(9),UInt(3),UInt(2),UInt(15))
	val s2_row3 = Vec(UInt(13),UInt(8),UInt(10),UInt(1),UInt(3),UInt(15),UInt(4),UInt(2),UInt(11),UInt(6),UInt(7),UInt(12),UInt(0),UInt(5),UInt(14),UInt(9))
    
	val s3_row0 = Vec(UInt(10),UInt(0),UInt(9),UInt(14),UInt(6),UInt(3),UInt(15),UInt(5),UInt(1),UInt(13),UInt(12),UInt(7),UInt(11),UInt(4),UInt(2),UInt(8))
	val s3_row1 = Vec(UInt(13),UInt(7),UInt(0),UInt(9),UInt(3),UInt(4),UInt(6),UInt(10),UInt(2),UInt(8),UInt(5),UInt(14),UInt(12),UInt(11),UInt(15),UInt(1))
	val s3_row2 = Vec(UInt(13),UInt(6),UInt(4),UInt(9),UInt(8),UInt(15),UInt(3),UInt(0),UInt(11),UInt(1),UInt(2),UInt(12),UInt(5),UInt(10),UInt(14),UInt(7))
	val s3_row3 = Vec(UInt(1),UInt(10),UInt(13),UInt(0),UInt(6),UInt(9),UInt(8),UInt(7),UInt(4),UInt(15),UInt(14),UInt(3),UInt(11),UInt(5),UInt(2),UInt(12))

	val s4_row0 = Vec(UInt(7),UInt(13),UInt(14),UInt(3),UInt(0),UInt(6),UInt(9),UInt(10),UInt(1),UInt(2),UInt(8),UInt(5),UInt(11),UInt(12),UInt(4),UInt(15))
	val s4_row1 = Vec(UInt(13),UInt(8),UInt(11),UInt(5),UInt(6),UInt(15),UInt(0),UInt(3),UInt(4),UInt(7),UInt(2),UInt(12),UInt(1),UInt(10),UInt(14),UInt(9))
	val s4_row2 = Vec(UInt(10),UInt(6),UInt(9),UInt(0),UInt(12),UInt(11),UInt(7),UInt(13),UInt(15),UInt(1),UInt(3),UInt(14),UInt(5),UInt(2),UInt(8),UInt(4))
	val s4_row3 = Vec(UInt(3),UInt(15),UInt(0),UInt(6),UInt(10),UInt(1),UInt(13),UInt(8),UInt(9),UInt(4),UInt(5),UInt(11),UInt(12),UInt(7),UInt(2),UInt(14))

	val s5_row0 = Vec(UInt(2),UInt(12),UInt(4),UInt(1),UInt(7),UInt(10),UInt(11),UInt(6),UInt(8),UInt(5),UInt(3),UInt(15),UInt(13),UInt(0),UInt(14),UInt(9))
	val s5_row1 = Vec(UInt(14),UInt(11),UInt(2),UInt(12),UInt(4),UInt(7),UInt(13),UInt(1),UInt(5),UInt(0),UInt(15),UInt(10),UInt(3),UInt(9),UInt(8),UInt(6))
	val s5_row2 = Vec(UInt(4),UInt(2),UInt(1),UInt(11),UInt(10),UInt(13),UInt(7),UInt(8),UInt(15),UInt(9),UInt(12),UInt(5),UInt(6),UInt(3),UInt(0),UInt(14))
	val s5_row3 = Vec(UInt(11),UInt(8),UInt(12),UInt(7),UInt(1),UInt(14),UInt(2),UInt(13),UInt(6),UInt(15),UInt(0),UInt(9),UInt(10),UInt(4),UInt(5),UInt(3))

	val s6_row0 = Vec(UInt(12),UInt(1),UInt(10),UInt(15),UInt(9),UInt(2),UInt(6),UInt(8),UInt(0),UInt(13),UInt(3),UInt(4),UInt(14),UInt(7),UInt(5),UInt(11))
	val s6_row1 = Vec(UInt(10),UInt(15),UInt(4),UInt(2),UInt(7),UInt(12),UInt(9),UInt(5),UInt(6),UInt(1),UInt(13),UInt(14),UInt(0),UInt(11),UInt(3),UInt(8))
	val s6_row2 = Vec(UInt(9),UInt(14),UInt(15),UInt(5),UInt(2),UInt(8),UInt(12),UInt(3),UInt(7),UInt(0),UInt(4),UInt(10),UInt(1),UInt(13),UInt(11),UInt(6))
	val s6_row3 = Vec(UInt(4),UInt(3),UInt(2),UInt(12),UInt(9),UInt(5),UInt(15),UInt(10),UInt(11),UInt(14),UInt(1),UInt(7),UInt(6),UInt(0),UInt(8),UInt(13))

	val s7_row0 = Vec(UInt(4),UInt(11),UInt(2),UInt(14),UInt(15),UInt(0),UInt(8),UInt(13),UInt(3),UInt(12),UInt(9),UInt(7),UInt(5),UInt(10),UInt(6),UInt(1))
	val s7_row1 = Vec(UInt(13),UInt(0),UInt(11),UInt(7),UInt(4),UInt(9),UInt(1),UInt(10),UInt(14),UInt(3),UInt(5),UInt(12),UInt(2),UInt(15),UInt(8),UInt(6))
	val s7_row2 = Vec(UInt(1),UInt(4),UInt(11),UInt(13),UInt(12),UInt(3),UInt(7),UInt(14),UInt(10),UInt(15),UInt(6),UInt(8),UInt(0),UInt(5),UInt(9),UInt(2))
	val s7_row3 = Vec(UInt(6),UInt(11),UInt(13),UInt(8),UInt(1),UInt(4),UInt(10),UInt(7),UInt(9),UInt(5),UInt(0),UInt(15),UInt(14),UInt(2),UInt(3),UInt(12))

	val s8_row0 = Vec(UInt(13),UInt(2),UInt(8),UInt(4),UInt(6),UInt(15),UInt(11),UInt(1),UInt(10),UInt(9),UInt(3),UInt(14),UInt(5),UInt(0),UInt(12),UInt(7))
	val s8_row1 = Vec(UInt(1),UInt(15),UInt(13),UInt(8),UInt(10),UInt(3),UInt(7),UInt(4),UInt(12),UInt(5),UInt(6),UInt(11),UInt(0),UInt(14),UInt(9),UInt(2))
	val s8_row2 = Vec(UInt(7),UInt(11),UInt(4),UInt(1),UInt(9),UInt(12),UInt(14),UInt(2),UInt(0),UInt(6),UInt(10),UInt(13),UInt(15),UInt(3),UInt(5),UInt(8))
	val s8_row3 = Vec(UInt(2),UInt(1),UInt(14),UInt(7),UInt(4),UInt(10),UInt(8),UInt(13),UInt(15),UInt(12),UInt(9),UInt(0),UInt(3),UInt(5),UInt(6),UInt(11)) 
    
    val s1 = Vec(s1_row0, s1_row1, s1_row2, s1_row3)
    val s2 = Vec(s2_row0, s2_row1, s2_row2, s2_row3)
    val s3 = Vec(s3_row0, s3_row1, s3_row2, s3_row3)
    val s4 = Vec(s4_row0, s4_row1, s4_row2, s4_row3)
    val s5 = Vec(s5_row0, s5_row1, s5_row2, s5_row3)
    val s6 = Vec(s6_row0, s6_row1, s6_row2, s6_row3)
    val s7 = Vec(s7_row0, s7_row1, s7_row2, s7_row3)
    val s8 = Vec(s8_row0, s8_row1, s8_row2, s8_row3)
    
    val a = io.cmd.bits.rs1
	
	val a1 = a(5,0)
	val a2 = a(11,6)
	val a3 = a(17,12)
	val a4 = a(23,18)
	val a5 = a(29,24)
	val a6 = a(35,30)
	val a7 = a(41,36)
	val a8 = a(47,42)
	
	val a1_x = Cat(a1(0),a1(5))
	val a2_x = Cat(a2(0),a2(5))
	val a3_x = Cat(a3(0),a3(5))
	val a4_x = Cat(a4(0),a4(5))
	val a5_x = Cat(a5(0),a5(5))
	val a6_x = Cat(a6(0),a6(5))
	val a7_x = Cat(a7(0),a7(5))
	val a8_x = Cat(a8(0),a8(5))
	
	val a1_y = a1(4,1)
	val a2_y = a2(4,1)
	val a3_y = a3(4,1)
	val a4_y = a4(4,1)
	val a5_y = a5(4,1)
	val a6_y = a6(4,1)
	val a7_y = a7(4,1)
	val a8_y = a8(4,1)
	
    val x = Cat(s1(a1_x)(a1_y),
				s2(a2_x)(a2_y), 
				s3(a3_x)(a3_y), 
				s4(a4_x)(a4_y),
				s5(a5_x)(a5_y), 
				s6(a6_x)(a6_y), 
				s7(a7_x)(a7_y), 
				s8(a8_x)(a8_y))
    
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
