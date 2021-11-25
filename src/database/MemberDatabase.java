package database;

import domain.member.Member;

import java.util.ArrayList;

public class MemberDatabase {

    private ArrayList<Member> members = new ArrayList<>();

    public void addMember(Member member){
        members.add(member);
    }

    public void deleteMember(Member member){
        members.remove(member);
    }

    public Member getMember(String name){
        for (Member member : members) {
            if (member.getName().equals(name)){
                return member;
            }
        }
        return null;
    }

    public Member getMember(int memberNumber){
        for (Member member : members) {
            if (member.getMemberNumber() == memberNumber){
                return member;
            }
        }
        return null;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }
}
